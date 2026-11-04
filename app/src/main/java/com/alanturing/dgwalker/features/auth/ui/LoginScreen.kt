package com.alanturing.dgwalker.features.auth.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alanturing.dgwalker.R

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit
) {
    val emailOrUsername = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.app_name)
            )
            //Text(
            // modifier = Modifier.clickable{onNavigateToForgotPassword()},
            //text = stringResource(id = R.string.forgot_password_button)
            //)
        }
        Box(
            contentAlignment = Alignment.CenterStart
        ){
            TextField(
                value = emailOrUsername.value,
                onValueChange = {emailOrUsername.value = it},
                label = { Text(stringResource(id = R.string.user_or_gmail_label)) }
            )
        }
        Spacer(
            modifier = Modifier.height(5.dp)
        )
        Box(
            contentAlignment = Alignment.CenterStart
        ){
            TextField(
                value = password.value,
                onValueChange = {password.value = it},
                label = { Text(stringResource(id = R.string.insert_password_label))}
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
                modifier = Modifier.clickable{onNavigateToRegister()},
                text = stringResource(id = R.string.register_label)
            )
            Text(
                modifier = Modifier.clickable{onNavigateToForgotPassword()},
                text = stringResource(id = R.string.forgot_password_label)
            )
        }
        Spacer(
            modifier = Modifier.height(10.dp)
        )
        Box(
            contentAlignment = Alignment.Center
        ){
            Text(
                modifier = Modifier.clickable{onNavigateToHome()},
                text = stringResource(id = R.string.login_button)
            )
        }
    }

}