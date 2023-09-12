package com.example.passwordmanager.feature_password.domain.use_cases

import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository

class AddPassword(
    private val passwordRepository: PasswordRepository
) {

    suspend operator fun invoke(password: Password){
        if (password.password.isNotBlank() && password.service.isNotBlank())
             passwordRepository.addPassword(password)
    }
}