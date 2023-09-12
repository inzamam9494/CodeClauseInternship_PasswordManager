package com.example.passwordmanager.feature_password.domain.use_cases

import android.util.Log
import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetPasswords(
    private val passwordRepository: PasswordRepository
) {

    operator fun invoke(): Flow<List<Password>>? {
        return passwordRepository.allPasswords()
    }
}