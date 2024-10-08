package br.com.asoncs.multi.passwords.di

import br.com.asoncs.multi.passwords.ui.login.LoginViewModel
import br.com.asoncs.multi.passwords.ui.login.LoginViewModelImpl
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun koinApplication() = koinApplication {
    modules(
        module {
            // ViewModels
            viewModel<LoginViewModel> {
                LoginViewModelImpl()
            }

            factory {
                Json {
                    ignoreUnknownKeys = true
                }
            }
        }
    )
}
