package com.cdb22dx005.loginwithretrofit.api

import retrofit2.Call
import retrofit2.http.GET

interface TransferInterface {
    @GET("dashboard")
    fun transferData() : Call<String>?
}