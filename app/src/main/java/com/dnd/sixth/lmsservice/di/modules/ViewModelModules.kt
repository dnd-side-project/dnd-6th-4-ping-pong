package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.presentation.main.home.HomeViewModel
import com.dnd.sixth.lmsservice.presentation.main.making.MakeClassViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        HomeViewModel()
    }
}

val makeClassModelModule = module {
    viewModel {
        MakeClassViewModel()
    }
}