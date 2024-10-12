package br.com.asoncs.multi.passwords.auth

import org.koin.dsl.module

fun authModule(
    auth: Auth
) = module {
    factory { auth }
}
