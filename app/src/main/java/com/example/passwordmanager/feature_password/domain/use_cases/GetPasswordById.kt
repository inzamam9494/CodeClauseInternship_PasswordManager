package com.example.passwordmanager.feature_password.domain.use_cases

import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository

class GetPasswordById(
    private val passwordRepository: PasswordRepository
) {

    suspend operator fun invoke(id: Int): Password{
        return passwordRepository.getPasswordById(id)
    }
}