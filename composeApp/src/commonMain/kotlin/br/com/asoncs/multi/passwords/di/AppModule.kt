package br.com.asoncs.multi.passwords.di

import br.com.asoncs.multi.passwords.auth.Auth
import br.com.asoncs.multi.passwords.auth.authModule
import br.com.asoncs.multi.passwords.data.dataModule
import br.com.asoncs.multi.passwords.ui.uiModule
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.koinApplication
import org.koin.dsl.module

fun koinApplication(
    auth: Auth,
    platformModule: Module
) = koinApplication {
    modules(
        platformModule,
        module {
            factory {
                Json {
                    ignoreUnknownKeys = true
                }
            }
        },
        dataModule(),
        authModule(auth),
        uiModule()
    )
}
