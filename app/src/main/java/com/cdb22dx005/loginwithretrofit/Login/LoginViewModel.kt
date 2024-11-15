package com.cdb22dx005.loginwithretrofit.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cdb22dx005.roomdatabase.LoginEntity1
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository): ViewModel() {

    fun insertUser(loginEntity: LoginEntity1) {
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.insertData(loginEntity)
        }
    }
}