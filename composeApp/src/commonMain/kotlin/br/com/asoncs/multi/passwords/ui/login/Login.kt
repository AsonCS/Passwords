package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.app_name
import passwords.composeapp.generated.resources.compose_multiplatform
import passwords.composeapp.generated.resources.login_screen_password
import passwords.composeapp.generated.resources.login_screen_password_placeholder
import passwords.composeapp.generated.resources.login_screen_username
import passwords.composeapp.generated.resources.login_screen_username_placeholder

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painterResource(Res.drawable.compose_multiplatform),
            null
        )
        Text(
            stringResource(Res.string.app_name)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = {
                Text(
                    stringResource(Res.string.login_screen_username)
                )
            },
            placeholder = {
                Text(
                    stringResource(Res.string.login_screen_username_placeholder)
                )
            }
        )
        TextField(
            value = "",
            onValueChange = {},
            label = {
                Text(
                    stringResource(Res.string.login_screen_password)
                )
            },
            placeholder = {
                Text(
                    stringResource(Res.string.login_screen_password_placeholder)
                )
            }
        )
    }
}
