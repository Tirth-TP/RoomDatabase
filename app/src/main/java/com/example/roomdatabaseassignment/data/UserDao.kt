package com.example.roomdatabaseassignment.data

import android.R
import android.provider.Settings
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table WHERE age > 50")
    fun readFilterData(): LiveData<List<User>>

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUser()

}

