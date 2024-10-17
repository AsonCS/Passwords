package br.com.asoncs.multi.passwords.ui

import br.com.asoncs.multi.passwords.ui.app.AppViewModel
import br.com.asoncs.multi.passwords.ui.app.AppViewModelImpl
import br.com.asoncs.multi.passwords.ui.home.HomeViewModel
import br.com.asoncs.multi.passwords.ui.home.HomeViewModelImpl
import br.com.asoncs.multi.passwords.ui.login.LoginViewModel
import br.com.asoncs.multi.passwords.ui.login.LoginViewModelImpl
import br.com.asoncs.multi.passwords.ui.user.UserViewModel
import br.com.asoncs.multi.passwords.ui.user.UserViewModelImpl
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun uiModule() = module {
    viewModel<AppViewModel> {
        AppViewModelImpl()
    }
    viewModel<HomeViewModel> {
        HomeViewModelImpl(
            repository = get()
        )
    }
    viewModel<LoginViewModel> {
        LoginViewModelImpl(
            auth = get()
        )
    }
    viewModel<UserViewModel> {
        UserViewModelImpl(
            auth = get()
        )
    }
}
