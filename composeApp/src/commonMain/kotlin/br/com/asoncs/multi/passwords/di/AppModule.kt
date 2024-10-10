package br.com.asoncs.multi.passwords.di

import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.ui.AppViewModel
import br.com.asoncs.multi.passwords.ui.AppViewModelImpl
import br.com.asoncs.multi.passwords.ui.login.LoginViewModel
import br.com.asoncs.multi.passwords.ui.login.LoginViewModelImpl
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun koinApplication(
    auth: Auth
) = koinApplication {
    modules(
        module {
            // ViewModels
            viewModel<AppViewModel> {
                AppViewModelImpl(
                    auth = get()
                )
            }
            viewModel<LoginViewModel> {
                LoginViewModelImpl(
                    auth = get()
                )
            }

            factory { auth }

            factory {
                Json {
                    ignoreUnknownKeys = true
                }
            }
        }
    )
}
