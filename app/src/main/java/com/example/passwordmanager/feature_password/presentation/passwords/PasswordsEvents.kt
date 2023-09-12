package com.example.passwordmanager.feature_password.presentation.passwords

import com.example.passwordmanager.feature_password.domain.model.Password

sealed class PasswordsEvents {
     data class DeletePassword(val password: Password): PasswordsEvents()
     object RestorePassword: PasswordsEvents()
}