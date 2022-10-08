/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.picloud.worker

import android.content.Context
import android.os.Environment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.http.FileContent
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import com.piappstudio.pimodel.PiSession
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


private object PiDriveConstant {
    const val APP_META_DATA = "Gift Register project resource"
    const val IMAGE_CONTENT = "image/*"
    const val JSON_CONTENT = "application/json"
}

@Singleton
class PiDriveManager @Inject constructor(@ApplicationContext val context: Context, val piSession: PiSession) {

    val spaces = listOf("appDataFolder")


    private fun getDriveService(): Drive? {
        GoogleSignIn.getLastSignedInAccount(context)?.let { googleAccount ->
            val credential = GoogleAccountCredential.usingOAuth2(
                context, listOf(DriveScopes.DRIVE_APPDATA)
            )
            credential.selectedAccount = googleAccount.account!!
            return Drive.Builder(
                NetHttpTransport(), GsonFactory.getDefaultInstance(),
                credential
            ).setApplicationName(piSession.appName)
                .build()
        }
        return null
    }


    private suspend fun createFolder() {
        withContext(Dispatchers.IO) {
            getDriveService()?.let { googleDrive->
                try {
                    val folderData = com.google.api.services.drive.model.File()
                    folderData.name = "GiftRegister"
                    folderData.mimeType = "application/vnd.google-apps.folder"
                    folderData.spaces = spaces
                    val folder = googleDrive.files().create(folderData).setFields("id").execute()
                    Timber.d("Folder Id: $folder")

                } catch (ex:Exception) {
                    Timber.e(ex)
                    ex.printStackTrace()
                }

            }
        }
    }

    suspend fun searchFile(fileName:String):List<File>? {
            Timber.d("accessDriveFiles")
            getDriveService()?.let { googleDriveService ->
                var pageToken: String? = null
                val result = googleDriveService.files().list().apply {
                    fields = "nextPageToken, files(id, name)"
                    pageToken = this.pageToken
                    spaces = spaces
                    q = "name=$fileName"
                }.execute()

                return@let result
            }
            return null

    }

    private suspend fun uploadFileToGDrive(type:String, file: File) {
        withContext(Dispatchers.IO) {
            getDriveService()?.let { googleDriveService ->
                try {
                    /* val localFileDirectory = File(getExternalFilesDir("backup")!!.toURI())
                 val actualFfile = File("${localFileDirectory}/$FILE_NAME_BACKUPP")*/
                    val gfile = com.google.api.services.drive.model.File()
                    gfile.name = file.name
                    gfile.parents = spaces
                    val fileContent = FileContent(type, file)
                    val result = googleDriveService.Files().create(gfile, fileContent).execute()
                    Timber.d("File has been updated loaded successfully: ${result.id}, ${result.name}, ${result.webContentLink}, ${result.webViewLink}")
                } catch (e: Exception) {
                    Timber.e(e)
                    e.printStackTrace()
                }

            } ?: Timber.e("Signin error - not logged in")
        }

    }

    suspend fun downloadFileFromGDrive(id: String) {
        withContext(Dispatchers.IO) {
            getDriveService()?.let { googleDriveService ->
                val gDriveFile = googleDriveService.Files().get(id).execute()

            } ?: Timber.e("Sign in error - not logged in")
        }
    }

    /**
     * spaces — A comma-separated list of spaces to query within the corpus. Supported values are drive, appDataFolder and photos. Here we use drive.
    fields — The information required about each file like id, name, etc.
    q — A query for filtering the file results. See the “Search for Files” guide for supported syntax.

    @link https://medium.com/android-dev-hacks/integrating-google-drive-api-in-android-applications-18024f42391c]*/
    private suspend fun accessDriveFiles() {
        withContext(Dispatchers.IO) {
            Timber.d("accessDriveFiles")
            getDriveService()?.let { googleDriveService ->
                var pageToken: String? = null
                do {
                    val result = googleDriveService.files().list().apply {
                        fields = "nextPageToken, files(id, name)"
                        pageToken = this.pageToken
                        spaces = spaces
                    }.execute()
                    Timber.d("Result : ${result.files.size}")
                    for (file in result.files) {
                        Timber.d("name=${file.name} id=${file.id}")
                    }
                } while (pageToken != null)
            }
        }
    }


    suspend fun syncFiles() {
        val pictureDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (pictureDir?.isDirectory == true) {
            val files = pictureDir.listFiles()
            files?.let {
                for (file in files) {
                    if(searchFile(file.name)?.isEmpty() == true) {
                        Timber.d("File name: ${file.name}")
                        uploadFileToGDrive(PiDriveConstant.IMAGE_CONTENT, file)
                    } else {
                        Timber.d("File already exist")
                    }
                }
            }

        }
        accessDriveFiles()
    }
}