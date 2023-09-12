package com.example.passwordmanager.feature_password.presentation.util

sealed class Screens(val route: String){
    object PasswordsScreen: Screens("passwords")
    object AddEditPasswordScreen: Screens("add_edit_password")
}
