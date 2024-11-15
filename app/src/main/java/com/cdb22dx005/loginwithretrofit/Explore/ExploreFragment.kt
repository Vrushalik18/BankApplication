package com.cdb22dx005.loginwithretrofit.Explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.cdb22dx005.loginwithretrofit.Dashboard.BASE_URL
import com.cdb22dx005.loginwithretrofit.databinding.FragmentExploreBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ExploreFragment : Fragment() {
    private lateinit var binding : FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        getExploreData()

        return binding.root
    }

    private fun getExploreData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ExploreInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<ExploreData?> {
            override fun onResponse(Call: Call<ExploreData?>, response: Response<ExploreData?>) {
                val responseBody: ExploreData? = response.body()!!
                true.also { binding.webView.settings.javaScriptEnabled = it }
                if (responseBody != null) {
                    binding.webView.loadUrl(responseBody.navigationUrl)
                }

            }

            override fun onFailure(call: Call<ExploreData?>, t: Throwable) {
                Log.d("DashBoardFragment", "onFailure " + t.message)
            }
        })
    }
}


