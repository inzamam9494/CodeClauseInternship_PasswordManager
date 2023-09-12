package com.example.passwordmanager.feature_password.data.repository

import com.example.passwordmanager.feature_password.data.data_source.PasswordDao
import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow

class PasswordRepositoryImpl(
    private val passwordDao: PasswordDao
): PasswordRepository {

    override fun allPasswords(): Flow<List<Password>> {
        return passwordDao.allPasswords()
    }

    override suspend fun getPasswordById(id: Int): Password {
        return passwordDao.getPasswordById(id)
    }

    override suspend fun addPassword(password: Password) {
            passwordDao.addPassword(password)
    }

    override suspend fun deletePassword(password: Password) {
        passwordDao.deletePassword(password)
    }
}