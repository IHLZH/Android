package com.example.kotlinstudy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinstudy.utils.db.entity.UserInfo
import com.example.kotlinstudy.repository.UserRepository

class MyViewModel : AndroidViewModel {
    //ViewModel通过Repository实现与room的交互
    lateinit var userRepository: UserRepository

    constructor(application: Application) : super(application){
        this.userRepository = UserRepository(application)
    }

    class MyViewModelFactory(private val application: Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
                return MyViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun insert(user : UserInfo) : LongArray{
        return userRepository.insert(user)
    }

    fun delete(user : UserInfo) : Int{
        return userRepository.delete(user)
    }

    fun update(user : UserInfo) : Int{
        return userRepository.update(user)
    }

    fun query() : LiveData<List<UserInfo>>{
        return userRepository.query()
    }
}