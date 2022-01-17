package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        MainViewModel()
    }
}