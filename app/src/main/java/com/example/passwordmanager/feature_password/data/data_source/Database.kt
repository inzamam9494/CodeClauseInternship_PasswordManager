package com.example.passwordmanager.feature_password.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.passwordmanager.feature_password.domain.model.Password

@Database(entities = [Password::class], version = 1)
abstract class Database: RoomDatabase() {

    abstract val passwordDao: PasswordDao
}