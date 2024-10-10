package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.auth.AuthState.LoggedIn
import br.com.asoncs.multi.passwords.auth.User
import br.com.asoncs.multi.passwords.ui.PreviewContainer
import br.com.asoncs.multi.passwords.ui.PreviewPixel7

@PreviewPixel7
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(ValuesProvider::class) showContent: Boolean
) {
    PreviewContainer {
        HomeScreen(
            initialShowContent = showContent,
            modifier = Modifier,
            props = props(),
            state = LoggedIn(
                User(
                    "Son",
                    "abc@com.br",
                    null,
                    "uid"
                )
            )
        )
    }
}

class ValuesProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(
        false,
        true
    )
}

@Composable
private fun props() = HomeProps(
    image = painterResource(R.drawable.compose_multiplatform),
    onLogout = {}
)
