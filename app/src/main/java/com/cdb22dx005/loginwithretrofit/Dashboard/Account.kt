package com.cdb22dx005.loginwithretrofit.Dashboard

data class Account(
    val accountBalance: Int,
    val accountToken: String,
    val accountType: String,
    var displayName: String,
    val index: Int
)