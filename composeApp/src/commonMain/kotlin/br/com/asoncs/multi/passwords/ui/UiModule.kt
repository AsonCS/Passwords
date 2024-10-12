package br.com.asoncs.multi.passwords.ui

import br.com.asoncs.multi.passwords.ui.home.HomeViewModel
import br.com.asoncs.multi.passwords.ui.home.HomeViewModelImpl
import br.com.asoncs.multi.passwords.ui.login.LoginViewModel
import br.com.asoncs.multi.passwords.ui.login.LoginViewModelImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun uiModule() = module {
    viewModel<AppViewModel> {
        AppViewModelImpl(
            auth = get()
        )
    }
    viewModel<HomeViewModel> {
        HomeViewModelImpl(
            auth = get(),
            repository = get()
        )
    }
    viewModel<LoginViewModel> {
        LoginViewModelImpl(
            auth = get()
        )
    }
}
