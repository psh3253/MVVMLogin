package com.astar.mvvmlogin.remote

import android.util.Log
import com.astar.mvvmlogin.ServerApi
import com.astar.mvvmlogin.ServerService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class UserRetrofit {
    fun login(userId: String, password: String): Int {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServerApi.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val serverService = retrofit.create(ServerService::class.java)
        return try {
            val response = serverService.loginRequest(userId, password).execute()
            if (response.body()?.code == 200) {
                0
            } else {
                3
            }
        } catch (e: IOException) {
            4
        }
    }
}