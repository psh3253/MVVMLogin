package com.astar.mvvmlogin.repository

import com.astar.mvvmlogin.ServerApi
import com.astar.mvvmlogin.UserService
import com.astar.mvvmlogin.dao.AppDatabase
import com.astar.mvvmlogin.model.Account
import com.astar.mvvmlogin.model.GenericResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserRepository(private val appDatabase: AppDatabase) {

    fun login(userId: String, password: String, onResult: (Int) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServerApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val userService = retrofit.create(UserService::class.java)
        userService.loginRequest(userId, password)
            .enqueue(object : Callback<GenericResponse> {
                override fun onResponse(
                    call: Call<GenericResponse>,
                    response: Response<GenericResponse>
                ) {
                   if(response.body()?.code == 200) {
                       onResult(0)
                   } else {
                       onResult(3)
                   }
                }

                override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                    onResult(4)
                }
            })
    }

    fun getSavedAccount(): Account?  {
        return appDatabase.userDao().select()
    }

    fun saveAccount(account: Account) {
        appDatabase.userDao().insert(account)
    }

    fun deleteAccount() {
        appDatabase.userDao().delete()
    }
}