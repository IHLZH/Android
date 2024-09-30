package com.example.kotlinstudy.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TestViewModel : AndroidViewModel {
    private var testNumber : MutableLiveData<Int>? = null

    constructor(application: Application) : super(application){

    }

    public fun getNum() : LiveData<Int>{
        if(testNumber == null){
            testNumber = object : MutableLiveData<Int>(0){}
        }
        return testNumber!!
    }

    public fun addNum(num : Int){
        testNumber?.value = testNumber?.value!! + num
    }
}