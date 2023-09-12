package com.example.passwordmanager.feature_password.presentation.passwords.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.passwordmanager.feature_password.domain.model.Password

@Composable
fun PasswordItem(
    modifier: Modifier = Modifier,
    password: Password,
    onDeleteClick: () -> Unit,
    onItemClick: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                onItemClick()
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            Text(
                text = password.service,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h5
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = password.password,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 20.dp)
            )

            IconButton(onClick = onDeleteClick, modifier = Modifier.align(Alignment.End)) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Password")
            }
        }
    }
}