package br.com.asoncs.multi.passwords.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.asoncs.multi.passwords.ui.component.Loading
import org.jetbrains.compose.resources.painterResource
import passwords.composeapp.generated.resources.Res
import passwords.composeapp.generated.resources.compose_multiplatform

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {
    SplashScreen(
        modifier = modifier,
        props = SplashProps(
            image = painterResource(Res.drawable.compose_multiplatform)
        )
    )
}

@Composable
internal fun SplashScreen(
    modifier: Modifier,
    props: SplashProps
) {
    Column(
        modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement
            .spacedBy(
                alignment = Alignment.CenterVertically,
                space = 16.dp
            )
    ) {
        Image(
            props.image,
            null,
            Modifier
                .size(300.dp)
        )
        Loading()
    }
}
