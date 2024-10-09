package br.com.asoncs.multi.passwords.ui.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.asoncs.multi.passwords.R
import br.com.asoncs.multi.passwords.ui.PreviewContainer
import br.com.asoncs.multi.passwords.ui.PreviewPixel7

@PreviewPixel7
@Composable
private fun SplashScreenPreview() {
    PreviewContainer {
        SplashScreen(
            modifier = Modifier,
            props = props()
        )
    }
}

@Composable
private fun props() = SplashProps(
    image = painterResource(R.drawable.compose_multiplatform)
)
