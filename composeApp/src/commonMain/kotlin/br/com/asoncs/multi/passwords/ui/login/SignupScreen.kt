package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.signup_screen

@Composable
fun SignupScreen(
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    SignupScreen(
        modifier = modifier,
        navigateToLogin = navigateToLogin,
        props = SignupProps(
            stringResource(Res.string.signup_screen)
        )
    )
}

@Composable
fun SignupScreen(
    modifier: Modifier,
    navigateToLogin: () -> Unit,
    props: SignupProps
) {
    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            props.text,
            Modifier
                .clickable {
                    navigateToLogin()
                },
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
