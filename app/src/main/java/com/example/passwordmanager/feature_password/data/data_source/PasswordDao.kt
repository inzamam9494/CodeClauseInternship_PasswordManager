package com.example.passwordmanager.feature_password.data.data_source

import androidx.room.*
import com.example.passwordmanager.feature_password.domain.model.Password
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Query("select * from password")
    fun allPasswords(): Flow<List<Password>>

    @Query("select * from password where id=:id")
    fun getPasswordById(id: Int): Password

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPassword(password: Password)

    @Delete
    suspend fun deletePassword(password: Password)
}