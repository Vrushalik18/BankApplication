package com.cdb22dx005.loginwithretrofit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cdb22dx005.loginwithretrofit.Login.LoginDao
import com.cdb22dx005.roomdatabase.LoginEntity1

@Database(entities = [LoginEntity1::class

],
    version = 1,
    exportSchema = false

)

abstract class LoginDatabase : RoomDatabase() {

    abstract fun loginDao() : LoginDao


    companion object {
        @Volatile
        private var INSTANCE : LoginDatabase?= null
        fun getDatabase (context: Context) : LoginDatabase {
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    LoginDatabase::class.java,
                    "bankDB"
                )
                    .build()
            }
            return INSTANCE!!
        }
    }
}