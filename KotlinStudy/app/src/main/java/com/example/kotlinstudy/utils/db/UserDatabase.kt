package com.example.kotlinstudy.utils.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinstudy.utils.db.dao.UserInfoDao
import com.example.kotlinstudy.utils.db.entity.UserInfo

@Database(version = 1, entities = [UserInfo::class],exportSchema=false)
@TypeConverters(UserInfo.Converters::class)
abstract class UserDatabase : RoomDatabase() {

    public abstract fun getUserInfoDao() : UserInfoDao

    companion object{
        private val DB_NAME = "users.db"

        private var userDatabase : UserDatabase? = null

        @Synchronized
        public fun getInstance(context : Context) : UserDatabase{
            if(userDatabase == null){
                userDatabase = Room.databaseBuilder(context.applicationContext, UserDatabase::class.java, DB_NAME)
                    .build()
            }
            return userDatabase!!
        }
    }
}