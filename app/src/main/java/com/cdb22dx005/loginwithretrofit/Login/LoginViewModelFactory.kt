package com.cdb22dx005.loginwithretrofit.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val loginRepository: LoginRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository) as T
    }
}