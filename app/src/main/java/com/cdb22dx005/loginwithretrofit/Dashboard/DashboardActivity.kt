package com.cdb22dx005.loginwithretrofit.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.cdb22dx005.loginwithretrofit.Explore.ExploreFragment
import com.cdb22dx005.loginwithretrofit.R
import com.cdb22dx005.loginwithretrofit.Transfer.TransferFragment
import com.cdb22dx005.loginwithretrofit.databinding.ActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

       supportFragmentManager.beginTransaction().replace(R.id.dashboard_frame_layout,HomeFragment()).commit()

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_Home -> {
                   supportFragmentManager.beginTransaction().apply {
                        replace(R.id.dashboard_frame_layout, HomeFragment())
                        commit()
                    }
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.ic_Transfer -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.dashboard_frame_layout, TransferFragment())
                        commit()
                    }
                    Toast.makeText(this, "Transfer", Toast.LENGTH_SHORT).show()
                }

                R.id.ic_Explore -> {
                  supportFragmentManager.beginTransaction().apply {
                        replace(R.id.dashboard_frame_layout, ExploreFragment())
                        commit()
                    }
                    Toast.makeText(this, "Explore", Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }
            true
        }

    }
}


