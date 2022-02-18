package com.example.roomdatabaseassignment.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()
    val readFilterData: LiveData<List<User>> = userDao.readFilterData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
     suspend fun delete(user: User){
        userDao.delete(user)
    }
    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }

}