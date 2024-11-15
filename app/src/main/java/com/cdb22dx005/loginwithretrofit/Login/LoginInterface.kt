package com.cdb22dx005.loginwithretrofit.Login

import com.cdb22dx005.loginwithretrofit.Login.LoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginInterface {
    @POST("login")

    fun postData(@Body LoginData: LoginData): Call<LoginData>
}