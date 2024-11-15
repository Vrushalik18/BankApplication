package com.cdb22dx005.loginwithretrofit.Transfer

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cdb22dx005.loginwithretrofit.Dashboard.HomeFragment
import com.cdb22dx005.loginwithretrofit.R
import com.cdb22dx005.loginwithretrofit.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val args = this.arguments
        val inputData = args?.getString("index")
        val inputData1 = args?.getString("accountType")
        val inputData2 = args?.getString("displayName")
        val inputData3 = args?.getString("accountBalance")
        binding.index.text = "Index ${inputData.toString()}"
        binding.accType.text = "Account Type - ${inputData1.toString()}"
        binding.disName.text = "Account Name - ${inputData2.toString()}"
        binding.accBal.text = "Available Balance - $${inputData3.toString()}"

        binding.backButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.dashboard_frame_layout,HomeFragment())?.commit()
        }

        val view  = binding.root

        return view
    }

}