package com.cdb22dx005.loginwithretrofit.Dashboard

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdb22dx005.loginwithretrofit.AccountAdapter
import com.cdb22dx005.loginwithretrofit.LoadingDialoge
import com.cdb22dx005.loginwithretrofit.R
import com.cdb22dx005.loginwithretrofit.Transfer.DetailsFragment
import com.cdb22dx005.loginwithretrofit.api.apiInterface
import com.cdb22dx005.loginwithretrofit.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://demo0971568.mockable.io/banking/"
class HomeFragment : Fragment() {

private lateinit var binding : FragmentHomeBinding

override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        getRetrofitData()
        return view
    }
        private fun getRetrofitData() {
            val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(apiInterface::class.java)

            val retrofitData = retrofitBuilder.getData()

            retrofitData.enqueue(object : Callback<MyData?> {
                override fun onResponse(Call: Call<MyData?>, response: Response<MyData?>) {
                    val responseBody: MyData? = response.body()!!
                    if (responseBody != null) {
                        Log.d("Retrofit", responseBody.toString())
                        val adapter = AccountAdapter(requireContext(), responseBody.accounts)
                        val loading = LoadingDialoge(requireActivity())
                        loading.startLoading()
                        val handler = Handler()
                        handler.postDelayed({ loading.isDismissed() },2000)
                        binding.recView.adapter = adapter
                        binding.recView.layoutManager = LinearLayoutManager(requireContext())
                        adapter.setOnItemClickListener(object: AccountAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {

                                val input = responseBody.accounts[position]
                                val fragment = DetailsFragment()
                                val bundle = Bundle()
                                bundle.putString("index", input.index.toString())
                                bundle.putString("accountType", input.accountType)
                                bundle.putString("displayName", input.displayName)
                                bundle.putString("accountBalance", input.accountBalance.toString())
                                fragment.arguments = bundle
                                fragmentManager?.beginTransaction()?.replace(R.id.dashboard_frame_layout,fragment)?.commit()
                            }
                        })
                    }
                }
                override fun onFailure(call: Call<MyData?>, t: Throwable) {
                    Log.d("DashBoardFragment", "onFailure " + t.message)
                }
            })
        }   
}


