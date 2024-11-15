package com.cdb22dx005.loginwithretrofit.Login

import androidx.room.Dao
import androidx.room.Insert

import com.cdb22dx005.roomdatabase.LoginEntity1

@Dao
interface LoginDao {
    @Insert
    suspend fun insertData(loginEntity: LoginEntity1)
}