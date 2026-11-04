package com.alanturing.dgwalker.features.auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alanturing.dgwalker.R
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun RegisterScreen(
    onNavigateToHome: () -> Unit,
    onNagivateToLogin: () -> Unit
) {
    val user = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                enabled = true,
                onClickLabel = null,
                role = null
            ) {
                focusManager.clearFocus()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                value = user.value,
                onValueChange = { user.value = it },
                label = { Text(text = stringResource(id = R.string.insert_user_label)) },
                modifier = Modifier
                    .width(300.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text(text = stringResource(id = R.string.insert_gmail_label)) },
                modifier = Modifier
                    .width(300.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = stringResource(id = R.string.insert_password_label)) },
                modifier = Modifier
                    .width(300.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            TextField(
                value = confirmPassword.value,
                onValueChange = { confirmPassword.value = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text(text = stringResource(id = R.string.confirm_insert_password_label)) },
                modifier = Modifier
                    .width(300.dp)
            )
        }
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text(
                modifier = Modifier.clickable{onNagivateToLogin()},
                text = stringResource(id = R.string.already_have_an_account),
            )
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )
        Box(

        ){
            Text(
                modifier = Modifier.clickable{onNavigateToHome()},
                text = stringResource(id = R.string.register_label),

            )
        }
    }
}

