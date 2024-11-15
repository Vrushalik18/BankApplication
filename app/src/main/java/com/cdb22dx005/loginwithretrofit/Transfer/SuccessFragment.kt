package com.cdb22dx005.loginwithretrofit.Transfer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cdb22dx005.loginwithretrofit.Dashboard.HomeFragment
import com.cdb22dx005.loginwithretrofit.R
import com.cdb22dx005.loginwithretrofit.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    private lateinit var binding : FragmentSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessBinding.inflate(inflater,container,false)
        val view  = binding.root

        val args = this.arguments
        val inputData = args?.getString("text")
        val inputData2 = args?.getString("fromAcc")
        val inputData3 = args?.getString("toAcc")
        binding.transferAmt.text = "Transfer Amount is ${inputData.toString()}"
        binding.frmAcc.text = inputData2.toString()
        binding.toAcc.text = inputData3.toString()

        binding.homebtn.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction().apply {
                    replace(R.id.dashboard_frame_layout, HomeFragment())
                    commit()
                }
        }
        return view
    }


}