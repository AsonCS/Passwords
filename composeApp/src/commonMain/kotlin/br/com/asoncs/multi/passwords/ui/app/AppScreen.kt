package br.com.asoncs.multi.passwords.ui.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.asoncs.multi.passwords.ui.navigation.AppNavHost

@Composable
fun AppScreen(
    appViewModel: AppViewModel,
    modifier: Modifier,
    navController: NavHostController
) {
    Scaffold(
        modifier,
        topBar = {
            AppTopBar(appViewModel)
        }
    ) {
        AppNavHost(
            appViewModel,
            modifier
                .fillMaxSize(),
            navController
        )
    }
}
