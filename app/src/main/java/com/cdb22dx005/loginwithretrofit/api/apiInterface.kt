package com.cdb22dx005.loginwithretrofit.api

import com.cdb22dx005.loginwithretrofit.Dashboard.MyData
import retrofit2.Call
import retrofit2.http.GET

interface apiInterface {
    @GET("dashboard")

    fun getData() : Call<MyData>
}