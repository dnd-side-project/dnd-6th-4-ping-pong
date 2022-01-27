package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.presentation.home.HomeViewModel
import com.dnd.sixth.lmsservice.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        HomeViewModel()
        LoginViewModel()
    }
}