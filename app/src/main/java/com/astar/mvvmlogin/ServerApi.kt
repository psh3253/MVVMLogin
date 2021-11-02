package com.astar.mvvmlogin

import com.astar.mvvmlogin.model.GenericResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class ServerApi {
    companion object {
        const val DOMAIN = "http://ec2-13-209-12-187.ap-northeast-2.compute.amazonaws.com:12301/"
    }
}

interface UserService {
    @POST("login")
    @FormUrlEncoded
    fun loginRequest(
        @Field("userid") userId: String,
        @Field("password") password: String
    ): Call<GenericResponse>
}