package com.astar.mvvmlogin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var userId = MutableLiveData("")
    var password = MutableLiveData("")
    var checkRemember = MutableLiveData(false)
    var isLogin = MutableLiveData(false)
}