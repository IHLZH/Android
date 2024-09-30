package com.example.kotlinstudy.utils.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.kotlinstudy.utils.db.entity.UserInfo

@Dao
interface UserInfoDao {

    @Query("select * from tb_user")
    fun getUsers() : LiveData<List<UserInfo>>

    @Insert
    fun saveUsers(vararg userInfo : UserInfo) : LongArray

    @Delete
    fun deleteUsers(vararg userInfo : UserInfo) : Int

    @Update
    fun updateUsers(vararg userInfo : UserInfo) : Int
}