package com.example.passwordmanager.feature_password.presentation.add_edit_password

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.passwordmanager.feature_password.presentation.add_edit_password.components.CustomTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddEditPasswordScreen(
    navController: NavController,
    viewModel: AddEditViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    val serviceState = viewModel.serviceState.value
    val passwordState = viewModel.passwordState.value
    val userNameState = viewModel.userNameState.value
    val notesState = viewModel.notesState.value

    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvents.collectLatest { event ->
            when (event){
                is AddEditViewModel.UIEvents.SavePassword -> {
                    navController.navigateUp()
                }
            }
        }
    })

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditPasswordEvent.SavePassword)
            }) {
                Icon(imageVector = Icons.Filled.Save, contentDescription = "Save Passord")
            }
        }
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column {

                    CustomTextField(
                        text = serviceState.text,
                        hint = serviceState.hint,
                        onTextChange = {
                            viewModel.onEvent(AddEditPasswordEvent.EnteringService(it))
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.MiscellaneousServices, contentDescription = "Service")
                        })

                Spacer(modifier = Modifier.padding(20.dp))

                    CustomTextField(
                        text = userNameState.text,
                        hint = userNameState.hint,
                        onTextChange = {
                            viewModel.onEvent(AddEditPasswordEvent.EnteringUserName(it))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "UserName"
                            )
                        })

                Spacer(modifier = Modifier.padding(20.dp))

                    CustomTextField(
                        text = passwordState.text,
                        hint = passwordState.hint,
                        onTextChange = {
                            viewModel.onEvent(AddEditPasswordEvent.EnteringPassword(it))
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Password,
                                contentDescription = "Password"
                            )
                        })

                Spacer(modifier = Modifier.padding(20.dp))

                    CustomTextField(
                        text = notesState.text,
                        hint = notesState.hint,
                        isNotes = true,
                        onTextChange = {
                            viewModel.onEvent(AddEditPasswordEvent.EnteringNotes(it))
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Filled.Notes, contentDescription = "Notes")
                        })
            }
        }
    }
}