/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.filtter

import androidx.lifecycle.ViewModel
import com.piappstudio.giftregister.R
import com.piappstudio.pimodel.ResourceHelper
import com.piappstudio.pinavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class FilterOption(
    val groupBy: Option = Option(title = "Gift Type"),
    val sortByAmount: Option = Option(title = "Ascending"),
    val sortByName: Option = Option(title = "Ascending")
)

data class Option(
    val title: String? = null,
    val subTitle: String? = null,
    var isSelected: Boolean = false,
    var previousOption: Option? = null
) {
    init {
        if (previousOption?.title?.equals(title, true) == true) {
            isSelected = true
        }
    }
}

@HiltViewModel
class SortScreenViewModel @Inject constructor(
    val navManager: NavManager,
    private val resourceHelper: ResourceHelper
) : ViewModel() {
    private val _mapOfOptions = MutableStateFlow(mutableMapOf<Option, List<Option>>())
    val mapOfOption: StateFlow<Map<Option, List<Option>>> = _mapOfOptions

    var currentFilterOption: FilterOption = FilterOption()

    fun loadPreviousOption(filerOption: FilterOption) {
        currentFilterOption = filerOption
        val mapOfTopOption = mutableMapOf<Option, List<Option>>()


        // Sort By Name configuration
        val lstNameSortOption = mutableListOf<Option>()

        lstNameSortOption.add(
            Option(
                title = resourceHelper.getString(R.string.ascending),
                previousOption = filerOption.sortByName
            )
        )
        lstNameSortOption.add(
            Option(
                title = resourceHelper.getString(R.string.descending),
                previousOption = filerOption.sortByName
            )
        )

        mapOfTopOption[Option(
            title = resourceHelper.getString(R.string.sort_by_name),
            subTitle = resourceHelper.getString(R.string.ascending),
            isSelected = true
        )] = lstNameSortOption


        // Sort By amount configuration
        val lstSortByAmount = mutableListOf<Option>()

        lstSortByAmount.add(
            Option(
                title = resourceHelper.getString(R.string.ascending),
                previousOption = filerOption.sortByAmount
            )
        )
        lstSortByAmount.add(
            Option(
                title = resourceHelper.getString(R.string.descending),
                previousOption = filerOption.sortByAmount
            )
        )

        mapOfTopOption[Option(
            title = resourceHelper.getString(R.string.sort_by_amount),
            subTitle = resourceHelper.getString(R.string.ascending),
            isSelected = true
        )] = lstSortByAmount


        // Sort by group configuration
        val groupByOption = mutableListOf<Option>()
        groupByOption.add(
            Option(
                title = resourceHelper.getString(R.string.gift_type),
                previousOption = filerOption.groupBy
            )
        )
        groupByOption.add(
            Option(
                title = resourceHelper.getString(R.string.option_place),
                previousOption = filerOption.groupBy
            )
        )
        mapOfTopOption[Option(
            title = resourceHelper.getString(R.string.group_by),
            subTitle = resourceHelper.getString(R.string.gift_type),
            isSelected = true
        )] = groupByOption

        _mapOfOptions.update { mapOfTopOption }

    }

    fun updateTopClick(option: Option) {

        /*val previousSubList = _mapOfOptions.value[option]
        val updatedOption = option.copy(isSelected = !option.isSelected)
        updateOption(updatedOption, previousSubList)*/
    }

    private fun updateOption(topOption: Option, subList: List<Option>?) {
        val duplicateMap = mutableMapOf<Option, List<Option>>()
        duplicateMap.putAll(_mapOfOptions.value)
        val key = duplicateMap.keys.first { it.title == topOption.title }
        duplicateMap.remove(key)

        subList?.let {
            duplicateMap[topOption] = it
        }
        _mapOfOptions.update { duplicateMap }
    }

    fun updateSubListClick(topOption: Option, subOption: Option) {
        val updatedSubOption = subOption.copy(isSelected = !subOption.isSelected)
        // Update filter option
        updateFilterOption(topOption, updatedSubOption)
    }

    private fun updateFilterOption(topOption: Option, subOption: Option) {
        when (topOption.title) {
            resourceHelper.getString(R.string.sort_by_name) -> {
                currentFilterOption = currentFilterOption.copy(sortByName = subOption)
            }
            resourceHelper.getString(R.string.sort_by_amount) -> {
                currentFilterOption = currentFilterOption.copy(sortByAmount = subOption)

            }
            resourceHelper.getString(R.string.group_by) -> {
                currentFilterOption = currentFilterOption.copy(groupBy = subOption)
            }
        }

        loadPreviousOption(currentFilterOption)

    }
}