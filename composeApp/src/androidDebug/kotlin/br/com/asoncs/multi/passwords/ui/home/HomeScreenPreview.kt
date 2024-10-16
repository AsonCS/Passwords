package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.core.model.GithubUser
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
            state = HomeState.Success(
                githubUser = GithubUser(
                    exampleCounter = 2,
                    id = 1,
                    login = "Son",
                    name = "Son"
                )
            )
        )
    }
}

private class ValuesProvider : PreviewParameterProvider<Boolean> {
    override val values = sequenceOf(
        false,
        true
    )
}

@Composable
private fun props() = HomeProps(
    image = painterResource(R.drawable.compose_multiplatform)
)
