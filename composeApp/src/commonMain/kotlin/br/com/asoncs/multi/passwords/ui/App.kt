package br.com.asoncs.multi.passwords.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.rememberNavController
import br.com.asoncs.multi.passwords.di.koinApplication
import br.com.asoncs.multi.passwords.ui.navigation.*
import kotlinx.coroutines.delay
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(::koinApplication) {
        MaterialTheme {
            val navController = rememberNavController()

            AppNavHost(navController)

            LaunchedEffect(true) {
                delay(3_000)
                navController.navigateTo(LoginNavDestination)
            }
        }
    }
}
