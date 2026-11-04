package com.alanturing.dgwalker.features.auth.ui

import android.app.LocaleManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alanturing.dgwalker.R


@Composable
fun ForgotPasswordScreen(

) {
    val focusManager = LocalFocusManager.current
    val emailOrUsername = remember {mutableStateOf("")}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
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
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.app_name)
            )
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.width(275.dp)
        ){
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.forgot_password_instruction_label)
            )
        }
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Box(
            contentAlignment = Alignment.CenterStart
        ){
         TextField(
             value = emailOrUsername.value,
             onValueChange = {emailOrUsername.value = it},
             label = {Text(text = stringResource(id = R.string.forgot_password_insert_user_or_gmail_label))}
         )
        }
    }
}