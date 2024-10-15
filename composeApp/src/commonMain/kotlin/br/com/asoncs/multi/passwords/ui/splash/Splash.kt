package br.com.asoncs.multi.passwords.ui.splash

import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.navigation.AppDestination

data object SplashDestination : AppDestination<Unit>(
    "splash"
) {
    override fun destination(
        args: Unit,
        builder: NavGraphBuilder
    ) {
        builder.composable(route) {
            SplashScreen()
        }
    }
}

internal class SplashProps(
    val image: Painter
)
