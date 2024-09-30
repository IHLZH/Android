package com.example.kotlinstudy.utils.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date
import java.util.Objects

@Entity(tableName = "tb_user")
class UserInfo {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    lateinit var userId: String
    @ColumnInfo(name = "user_name")
    var userName: String? = null
    var sex: String? = null
    var birthday: Date? = null
    var height = 0
    var weight = 0f

    @Ignore
    constructor()

    constructor(
        userId: String,
        userName: String?,
        sex: String?,
        birthday: Date?,
        height: Int,
        weight: Float
    ) {
        this.userId = userId
        this.userName = userName
        this.sex = sex
        this.birthday = birthday
        this.height = height
        this.weight = weight
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val userInfo = o as UserInfo
        return height == userInfo.height && java.lang.Float.compare(
            userInfo.weight,
            weight
        ) == 0 && userId == userInfo.userId && userName == userInfo.userName && sex == userInfo.sex && birthday == userInfo.birthday
    }

    override fun hashCode(): Int {
        return Objects.hash(userId, userName, sex, birthday, height, weight)
    }

    override fun toString(): String {
        return "UserInfo(userId='$userId', userName=$userName, sex=$sex, birthday=$birthday, height=$height, weight=$weight)"
    }

    class Converters{

        @TypeConverter
        fun fromTimestamp(value : Long?) : Date?{
            return if(value == null) null else Date(value)
        }

        @TypeConverter
        fun toTimestamp(date : Date?) : Long?{
            return date?.time
        }
    }


}
