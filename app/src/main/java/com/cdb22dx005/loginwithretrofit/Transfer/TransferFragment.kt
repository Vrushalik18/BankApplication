package com.cdb22dx005.loginwithretrofit.Transfer



import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.cdb22dx005.loginwithretrofit.Dashboard.Account
import com.cdb22dx005.loginwithretrofit.LoadingDialoge
import com.cdb22dx005.loginwithretrofit.R
import com.cdb22dx005.loginwithretrofit.api.TransferInterface
import com.cdb22dx005.loginwithretrofit.databinding.FragmentTransferBinding
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


class TransferFragment : Fragment() {
 private lateinit var binding: FragmentTransferBinding
    private var modelArrayList: ArrayList<Account>? = null
    private val accountTypes = ArrayList<String>()
    private var fromSpinner: Spinner? = null
    private var toSpinner: Spinner? = null
    var selectedSpinner1 = ""
    var selectedSpinner2 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransferBinding.inflate(inflater, container, false)
        val view = binding.root

        fromSpinner = binding.fromacc
        toSpinner = binding.toacc

        getTransferData()

        return view

    }

    private fun getTransferData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://demo0971568.mockable.io/banking/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TransferInterface::class.java)

        val call = retrofit.transferData()
        call!!.enqueue(object : Callback<String?> {

            override fun onResponse(call: Call<String?>, response: Response<String?>) {

                Log.i("Respondents", response.body().toString())

                if (response.isSuccessful) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString())
                        val responseBody = response.body().toString()

                        spinJSON(responseBody)

                    } else {
                        Log.i(
                            "onEmptyResponse",
                            "Returned empty response"
                        )
                    }
                }
            }

        //    @SuppressLint("SuspiciousIndentation")
            @SuppressLint("SuspiciousIndentation")
            private fun spinJSON(response: String) {
                try {
                    val obj = JSONObject(response)

                    modelArrayList = ArrayList()
                    val dataArray = obj.getJSONArray("accounts")
                    for (i in 0 until dataArray.length()) {
                        val spinnerModel = Account(10, "", "", "name", 1)
                        val dataObj = dataArray.getJSONObject(i)
                        spinnerModel.displayName = dataObj.getString("displayName")

                        modelArrayList!!.add(spinnerModel)

                    }
                    for (i in modelArrayList!!.indices) {
                        accountTypes.add(modelArrayList!![i].displayName)
                    }
                    val spinnerArrayAdapter =
                        ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, accountTypes)

                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                    fromSpinner!!.adapter = spinnerArrayAdapter
                    toSpinner!!.adapter = spinnerArrayAdapter

                    binding.fromacc.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                selectedSpinner1 = parent?.getItemAtPosition(position).toString()
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {}
                        }

                    binding.toacc.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>?,
                                view: View?,
                                position: Int,
                                id: Long
                            ) {
                                selectedSpinner2 = parent?.getItemAtPosition(position).toString()
                            }

                            override fun onNothingSelected(parent: AdapterView<*>?) {}
                        }


                  binding.transferbtn.setOnClickListener {
                        val inputValue = binding.transferEditText.text.toString()
                      if (inputValue.isNotEmpty()) {
                        val bundle = Bundle()
                        bundle.putString("text", inputValue)
                        bundle.putString("fromAcc", selectedSpinner1)
                        bundle.putString("toAcc", selectedSpinner2)

                          val loading = LoadingDialoge(requireActivity())
                          loading.startLoading()
                          val handler = Handler()
                          handler.postDelayed(object : Runnable{
                              override fun run() {
                                  loading.isDismissed()
                              }

                          },2000)
                        val fragment2 = SuccessFragment()
                        fragment2.arguments = bundle
                          requireActivity().supportFragmentManager.beginTransaction().apply {
                              replace(R.id.dashboard_frame_layout, fragment2)
                              commit()
                          }
                      }
                      else
                      {
                          val builder = AlertDialog.Builder(requireContext())
                          builder.setTitle("Please Enter Amount")
                          builder.setPositiveButton("Ok")
                          { _, _ ->
                              Toast.makeText(requireContext(), "Ok", Toast.LENGTH_LONG)
                                  .show()
                          }
                          val alertDialog: AlertDialog = builder.create()
                          alertDialog.setCancelable(false)
                          alertDialog.show()
                      }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            override fun onFailure(call: Call<String?>, t: Throwable) {}
        })
    }
}