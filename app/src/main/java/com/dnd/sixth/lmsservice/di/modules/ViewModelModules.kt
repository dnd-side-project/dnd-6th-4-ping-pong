package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.lesson.progress.ClassProgressViewModel
import com.dnd.sixth.lmsservice.presentation.login.LoginViewModel
import com.dnd.sixth.lmsservice.presentation.main.MainViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.ClassManageViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.manage.making.MakeClassViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.add.ScheduleAddViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.HomeCalendarViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.manage.HomeClassViewModel
import com.dnd.sixth.lmsservice.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        LoginViewModel()
    }
    viewModel {
        MainViewModel()
    }
    viewModel {
        ClassManageViewModel()
    }
    viewModel {
        MakeClassViewModel()
    }
    viewModel {
        HomeCalendarViewModel()
    }
    viewModel {
        ScheduleAddViewModel()
    }
    viewModel {
        ClassProgressViewModel()
    }
    viewModel {
        StartFeedBackViewModel()
    }
    viewModel {
        WriteFeedBackViewModel()
    }
    viewModel {
        HomeViewModel()
    }
    viewModel {
        HomeClassViewModel()
    }

}
