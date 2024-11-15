package com.cdb22dx005.loginwithretrofit.Explore

import com.cdb22dx005.loginwithretrofit.Explore.ExploreData
import retrofit2.Call
import retrofit2.http.GET

interface ExploreInterface {

    @GET("user-session")

    fun getData() : Call<ExploreData>
}