package com.example.passwordmanager.feature_password.presentation.add_edit_password

sealed class AddEditPasswordEvent{
    data class EnteringService(val value: String): AddEditPasswordEvent()
    data class EnteringPassword(val value: String): AddEditPasswordEvent()
    data class EnteringUserName(val value: String): AddEditPasswordEvent()
    data class EnteringNotes(val value: String): AddEditPasswordEvent()
    object SavePassword: AddEditPasswordEvent()
}
