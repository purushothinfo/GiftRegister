/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.picloud.ui.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.drive.Drive
import com.piappstudio.pimodel.Constant
import com.piappstudio.pimodel.pref.PiPrefKey
import com.piappstudio.pimodel.pref.PiPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class AuthState(val isUserSignIn:Boolean = false, val currentUser:GoogleSignInAccount? = null, val syncDate:String?=null)
@HiltViewModel
class AuthIntroViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val piPreference: PiPreference
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState:StateFlow<AuthState> = _uiState.asStateFlow()

    private val gso = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestProfile()
        .requestScopes(Drive.SCOPE_FILE, Drive.SCOPE_APPFOLDER)
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    init {
        initializeSignIn()
    }

    private fun initializeSignIn() {
        viewModelScope.launch {
            val account = GoogleSignIn.getLastSignedInAccount(context)
            _uiState.update { it.copy(currentUser = account, isUserSignIn = account!=null) }
            lastSyncDate()
        }
    }

    fun updateAccountInfo(account: GoogleSignInAccount?) {
        _uiState.update { it.copy(currentUser = account, isUserSignIn = account!=null) }
    }

    fun performLogout() {
        piPreference.remove(PiPrefKey.LAST_SYNC_TIME)
        googleSignInClient.signOut()
        initializeSignIn()
    }

    private fun lastSyncDate() {
        val lstSyncTime = piPreference.getString(PiPrefKey.LAST_SYNC_TIME)
        _uiState.update { it.copy(syncDate = lstSyncTime) }
    }

    fun syncNow() {
        val date = Constant.PiFormat.orderItemDisplay.format(Date())
        piPreference.save(PiPrefKey.LAST_SYNC_TIME, date)
        lastSyncDate()
    }
}