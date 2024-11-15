package com.cdb22dx005.loginwithretrofit

import android.app.Activity
import android.app.AlertDialog


class LoadingDialoge(val mActivity: Activity) {
        private lateinit var   isdialog : AlertDialog
        fun startLoading(){
            val inflater = mActivity.layoutInflater
            val dialogView  = inflater.inflate(R.layout.loading,null)
            val builder = AlertDialog.Builder(mActivity)
            builder.setView(dialogView)
            builder.setCancelable(false)
            isdialog =builder.create()
            isdialog.show()
        }


    fun isDismissed(){

            isdialog.dismiss()

        }
    }
