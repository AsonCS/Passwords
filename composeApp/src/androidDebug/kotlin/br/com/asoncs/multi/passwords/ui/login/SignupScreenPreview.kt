package br.com.asoncs.multi.passwords.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.ui.*

@PreviewPixel7
@PreviewPtBr
@Composable
private fun SignupScreenPreview() {
    PreviewContainer {
        SignupScreen(
            modifier = Modifier,
            navigateToLogin = {},
            props = props()
        )
    }
}

@Composable
private fun props() = SignupProps(
    text = stringResource(R.string.signup_screen)
)
