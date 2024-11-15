package com.cdb22dx005.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login1")
data class LoginEntity1(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val username : String,
    val password : String
)
