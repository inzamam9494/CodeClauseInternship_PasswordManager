package com.example.passwordmanager.feature_password.presentation.add_edit_password.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    isNotes: Boolean = false,
    textStyle: TextStyle = TextStyle(),
    onTextChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
) {

    TextField(
        modifier = modifier.fillMaxWidth(0.8f),
        value = text,
        onValueChange = onTextChange,
        textStyle = textStyle,
        leadingIcon = leadingIcon,
        singleLine = !isNotes,
        label = {
            Text(text = hint)
        },
    )
}