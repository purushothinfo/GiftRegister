/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.ui.event.about

import androidx.lifecycle.ViewModel
import com.piappstudio.pimodel.EventSummary
import com.piappstudio.pinavigation.NavManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(val navManager: NavManager): ViewModel(){

}