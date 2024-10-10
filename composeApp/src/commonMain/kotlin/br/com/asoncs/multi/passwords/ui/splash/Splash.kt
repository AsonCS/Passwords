package br.com.asoncs.multi.passwords.ui.splash

import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.navigation.AppDestination

data object SplashDestination : AppDestination("splash")

internal class SplashProps(
    val image: Painter
)

fun NavGraphBuilder.splashDestination() {
    composable(route = SplashDestination.route) {
        SplashScreen()
    }
}
