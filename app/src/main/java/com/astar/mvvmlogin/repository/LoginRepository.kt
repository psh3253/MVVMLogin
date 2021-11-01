package com.astar.mvvmlogin.repository

import com.astar.mvvmlogin.dao.UserDao
import com.astar.mvvmlogin.remote.UserRetrofit
import com.astar.mvvmlogin.viewmodel.LoginViewModel

class LoginRepository private constructor(val userDao: UserDao) {
    companion object {
        @Volatile private var INSTANCE: LoginRepository? = null
        private val userRetrofit = UserRetrofit()

        fun getInstance(userDao: UserDao): LoginRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LoginRepository(userDao).also { INSTANCE = it }
            }
    }

    fun login(userId: String, password: String): Int {
        return userRetrofit.login(userId, password)
    }


}