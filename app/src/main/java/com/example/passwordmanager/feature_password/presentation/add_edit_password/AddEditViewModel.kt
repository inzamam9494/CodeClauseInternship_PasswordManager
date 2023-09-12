package com.example.passwordmanager.feature_password.presentation.add_edit_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _serviceState = mutableStateOf(PasswordTextFieldState(hint = "Service"))
    val serviceState: State<PasswordTextFieldState> = _serviceState

    private val _passwordState = mutableStateOf(PasswordTextFieldState(hint = "Password"))
    val passwordState: State<PasswordTextFieldState> = _passwordState

    private val _userNameState = mutableStateOf(PasswordTextFieldState(hint = "Username"))
    val userNameState: State<PasswordTextFieldState> = _userNameState

    private val _notesState = mutableStateOf(PasswordTextFieldState(hint = "Notes..."))
    val notesState: State<PasswordTextFieldState> = _notesState

    private var _uiEvent = MutableSharedFlow<UIEvents>()
    val uiEvents: SharedFlow<UIEvents> = _uiEvent

    private var pressedPasswordId: Int? = null

    init {
        savedStateHandle.get<Int>("passwordId")?.let { passwordId ->
            if (passwordId != -1){
                pressedPasswordId = passwordId
                viewModelScope.launch(Dispatchers.IO) {
                    delay(100)
                    useCases.getPasswordById(passwordId).let { password ->
                        _serviceState.value = _serviceState.value.copy(
                            text = password.service,
                        )
                        _passwordState.value = _passwordState.value.copy(
                            text = password.password,
                        )
                        password.userName?.let { userName ->
                            _userNameState.value = _userNameState.value.copy(
                                text = userName,
                            )
                        }
                        password.note?.let { notes ->
                            _notesState.value = _notesState.value.copy(
                                text = notes,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditPasswordEvent){
        when (event){
            is AddEditPasswordEvent.SavePassword -> {
                viewModelScope.launch {
                    useCases.addPassword(Password(
                        id = pressedPasswordId,
                        service = _serviceState.value.text,
                        password = _passwordState.value.text,
                        userName = _userNameState.value.text,
                        note = _notesState.value.text
                    ))
                    _uiEvent.emit(UIEvents.SavePassword)
                }
            }

            is AddEditPasswordEvent.EnteringService -> {
                    _serviceState.value = _serviceState.value.copy(
                        text = event.value
                    )
            }

            is AddEditPasswordEvent.EnteringPassword -> {
                    _passwordState.value = _passwordState.value.copy(
                        text = event.value
                    )
            }

            is AddEditPasswordEvent.EnteringUserName -> {
                _userNameState.value = _userNameState.value.copy(
                    text = event.value
                )
            }

            is AddEditPasswordEvent.EnteringNotes -> {
                _notesState.value = _notesState.value.copy(
                    text = event.value
                )
            }
        }
    }

    sealed class UIEvents {
        object SavePassword: UIEvents()
    }
}