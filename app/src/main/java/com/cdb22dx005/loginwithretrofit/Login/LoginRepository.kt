package com.cdb22dx005.loginwithretrofit.Login

import com.cdb22dx005.roomdatabase.LoginEntity1


class LoginRepository(private val loginDao: LoginDao) {

    suspend fun insertData (loginEntity: LoginEntity1){
        loginDao.insertData(loginEntity)
    }
}