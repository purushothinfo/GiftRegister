/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.guestlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piappstudio.giftregister.R
import com.piappstudio.giftregister.ui.event.filtter.FilterOption
import com.piappstudio.pimodel.Constant.EMPTY_STRING
import com.piappstudio.pimodel.GuestInfo
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.ResourceHelper
import com.piappstudio.pimodel.database.PiDataRepository
import com.piappstudio.pinavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


data class GuestListState(
    val lstGuest: List<GuestInfo>,
    val searchOption: SearchOption,
    val filteredItem: Map<String, List<GuestInfo>>
)

@HiltViewModel
class GuestListViewModel @Inject constructor(
    private val piDataRepository: PiDataRepository,
    val navManager: NavManager,
    private val resourceHelper: ResourceHelper
) : ViewModel() {

    var selectedEventId: Long = 0


    private val _lstGuest: MutableStateFlow<List<GuestInfo>> = MutableStateFlow(emptyList())

    private val _guestListState =
        MutableStateFlow(
            GuestListState(
                lstGuest = emptyList(),
                searchOption = SearchOption(),
                emptyMap()
            )
        )
    val guestListState: StateFlow<GuestListState> = _guestListState


    private val _selectedGiftInfo: MutableStateFlow<GuestInfo> = MutableStateFlow(GuestInfo())
    val selectedGiftInfo: StateFlow<GuestInfo> = _selectedGiftInfo
    fun updateSelectedGiftInfo(guestInfo: GuestInfo?) {
        _selectedGiftInfo.update { guestInfo ?: GuestInfo() }
    }

    fun fetchGuest() {
        viewModelScope.launch(Dispatchers.IO) {
            Timber.d("Fetching event for $selectedEventId")
            piDataRepository.fetchGuest(selectedEventId).onEach { result ->
                Timber.d("Status ${result.status}")
                if (result.status == Resource.Status.SUCCESS) {
                    result.data?.let { guestList ->
                        Timber.d("Data size: ${guestList.size}")
                        _lstGuest.value = guestList
                        _guestListState.update { it.copy(lstGuest = guestList) }
                        applyFilter()
                    }
                }

            }.collect()

        }

    }

    fun updateSearchText(text: String) {
        _guestListState.update { it.copy(searchOption = it.searchOption.copy(text = text)) }
        val lstFiltered = _lstGuest.value.filter {
            it.name?.contains(text, true) == true
                    || it.address?.contains(text, true) == true
        }
        _guestListState.update { it.copy(lstGuest = lstFiltered) }
        applyFilter()
    }

    fun updateFilter(updatedOption: FilterOption) {
        _guestListState.update { it.copy(searchOption = it.searchOption.copy(filterOption = updatedOption)) }
        applyFilter()

    }

    fun applyFilter() {
        val filterOption = _guestListState.value.searchOption.filterOption
        val fullList = _guestListState.value.lstGuest

        val giftType = resourceHelper.getString(R.string.gift_type)

        // Applied GiftType
        var groupedItem: Map<String, List<GuestInfo>> =
            if (filterOption.groupBy == null || filterOption.groupBy.title == giftType) {
                fullList.groupBy { it.giftType.toString() }
            } else {
                fullList.groupBy { it.address ?: EMPTY_STRING }
            }

        val finalSortedMap = mutableMapOf<String, List<GuestInfo>>()


        val ascending = resourceHelper.getString(R.string.ascending)
        val descending = resourceHelper.getString(R.string.descending)

        val nameOrder = filterOption.sortByName.title
        val amountOrder = filterOption.sortByAmount.title

        for ((key, value) in groupedItem) {
            var sortedArray:List<GuestInfo> = emptyList()
            if (amountOrder == ascending && nameOrder == ascending) {
                sortedArray = value.sortedWith(compareBy<GuestInfo> { it.giftValue?.toDouble() }.thenBy { it.name })
            } else if (amountOrder == ascending && nameOrder == descending) {
                sortedArray = value.sortedWith(compareBy<GuestInfo> { it.giftValue?.toDouble() }.thenByDescending { it.name } )
            } else if (amountOrder == descending && nameOrder == descending) {
                sortedArray = value.sortedWith(compareByDescending<GuestInfo> { it.giftValue?.toDouble() }.thenByDescending { it.name })
            } else if (amountOrder == descending && nameOrder == ascending) {
                sortedArray = value.sortedWith(compareByDescending<GuestInfo> { it.giftValue?.toDouble() }.thenBy {  it.name } )

            }
            finalSortedMap[key] = sortedArray
        }


        _guestListState.update { it.copy(filteredItem = finalSortedMap) }

    }
}


/**
 * Map<Key, Value>
 *    Map<String, List<Guest>
 *       ["Cash" -> [
 *       "Gund",
 *       "Marsha",
 *       "Gowtham"
 *       ]
 *       ["Gold"-> [ "Suresh"]
 *
 *
 *
 *
 * */


data class SearchOption(val text: String? = null, val filterOption: FilterOption = FilterOption()) {

    fun getFilterCount(): String {
        var count = 0
        if (filterOption.groupBy != null) {
            count++
        }
        if (filterOption.sortByName != null) {
            count++
        }
        if (filterOption.sortByAmount != null) {
            count++
        }
        return if (count == 0) {
            EMPTY_STRING
        } else {
            count.toString()
        }
    }
}

