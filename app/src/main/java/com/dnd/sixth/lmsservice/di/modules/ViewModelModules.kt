package com.dnd.sixth.lmsservice.di.modules



import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.lesson.progress.ClassProgressViewModel
import com.dnd.sixth.lmsservice.presentation.login.LoginViewModel
import com.dnd.sixth.lmsservice.presentation.main.MainViewModel
import com.dnd.sixth.lmsservice.presentation.main.home.HomeViewModel
import com.dnd.sixth.lmsservice.presentation.main.home.making.MakeClassViewModel
import com.dnd.sixth.lmsservice.presentation.main.schedule.calendar.CalendarViewModel
import com.dnd.sixth.lmsservice.presentation.main.schedule.add.ScheduleAddViewModel
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
        HomeViewModel()
    }
    viewModel {
        MakeClassViewModel()
    }
    viewModel {
        CalendarViewModel()
    }
    viewModel {
        ScheduleAddViewModel()
    }
    viewModel {
        ClassProgressViewModel()
    }

    viewModel{
        StartFeedBackViewModel()
    }
    viewModel {
        WriteFeedBackViewModel()
    }

}
