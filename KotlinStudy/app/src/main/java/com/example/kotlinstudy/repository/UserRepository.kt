package com.example.kotlinstudy.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinstudy.utils.db.dao.UserInfoDao
import com.example.kotlinstudy.utils.db.entity.UserInfo
import com.example.kotlinstudy.utils.db.UserDatabase

class UserRepository {
    private lateinit var userDao : UserInfoDao

    constructor(context : Context){
        var userDatabase = UserDatabase.getInstance(context)
        this.userDao = userDatabase.getUserInfoDao()
    }

    fun insert(user : UserInfo) : LongArray{
        return userDao.saveUsers(user)
    }

    fun delete(user : UserInfo) : Int{
        return userDao.deleteUsers(user)
    }

    fun update(user : UserInfo) : Int{
        return userDao.updateUsers(user)
    }

    fun query() : LiveData<List<UserInfo>>{
        return userDao.getUsers()
    }


}