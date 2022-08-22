/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.giftregister.data

import androidx.room.*


@Dao
interface UserDao {
    @Query("SELECT * FROM userinfo ORDER BY (title=title OR date=date OR cashAmount=cashAmount " +
            "OR totalGold=totalGold)and totalOthers=totalOthers")
    fun getAll(): List<UserInfo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userInfo: UserInfo)

    @Delete
    suspend fun deleteAll(userInfo: UserInfo)

    @Update
    suspend fun update(userInfo: UserInfo): Int


}