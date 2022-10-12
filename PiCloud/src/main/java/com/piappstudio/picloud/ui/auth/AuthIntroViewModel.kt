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
import com.piappstudio.picloud.worker.PiDriveManager
import com.piappstudio.pimodel.Constant
import com.piappstudio.pimodel.Resource
import com.piappstudio.pimodel.pref.PiPrefKey
import com.piappstudio.pimodel.pref.PiPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

data class AuthState(
    val isUserSignIn: Boolean = false,
    val currentUser: GoogleSignInAccount? = null,
    val syncDate: String? = null,
    val isLoading:Boolean = false
)

@HiltViewModel
class AuthIntroViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val piPreference: PiPreference,
    private val piDriveManager: PiDriveManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthState())
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

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
            _uiState.update { it.copy(currentUser = account, isUserSignIn = account != null) }
            lastSyncDate()
        }
    }

    fun updateAccountInfo(account: GoogleSignInAccount?) {
        _uiState.update { it.copy(currentUser = account, isUserSignIn = account != null) }
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
        viewModelScope.launch(Dispatchers.IO) {
            piDriveManager.doSync().onEach { backupResult ->
                if (backupResult.status == Resource.Status.LOADING) {
                    _uiState.update { it.copy(isLoading = true) }
                    _uiState.update { it.copy(syncDate = backupResult.data) }
                } else {
                    _uiState.update { it.copy(isLoading = false) }
                }
                if (backupResult.status == Resource.Status.SUCCESS) {
                    val date = Constant.PiFormat.orderItemDisplay.format(Date())
                    piPreference.save(PiPrefKey.LAST_SYNC_TIME, date)
                    lastSyncDate()
                }
            }.collect()

        }
    }


}