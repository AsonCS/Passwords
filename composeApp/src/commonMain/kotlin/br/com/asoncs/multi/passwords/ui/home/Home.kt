package br.com.asoncs.multi.passwords.ui.home

import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.asoncs.multi.passwords.ui.navigation.AppDestination

data object HomeDestination : AppDestination("home")

class HomeProps(
    val image: Painter,
    val onLogout: () -> Unit
)

fun NavGraphBuilder.homeDestination() {
    composable(route = HomeDestination.route) {
        HomeScreen()
    }
}
