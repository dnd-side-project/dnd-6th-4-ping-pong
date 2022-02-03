package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.home.HomeViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.ClassViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.HomeCalendarViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.add.ClassAddViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.detail.ScheduleDetailViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.detail.request.EditRequestViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.manage.ClassManageViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.manage.making.MakeClassViewModel
import com.dnd.sixth.lmsservice.presentation.lesson.progress.ClassProgressViewModel
import com.dnd.sixth.lmsservice.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        LoginViewModel()
    }
    viewModel {
        ClassViewModel()
    }
    viewModel {
        MakeClassViewModel()
    }
    viewModel {
        HomeCalendarViewModel()
    }
    viewModel {
        ClassAddViewModel()
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
        ClassManageViewModel()
    }
    viewModel {
        EditRequestViewModel()
    }
    viewModel {
        ScheduleDetailViewModel()
    }

}
