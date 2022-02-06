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
import com.dnd.sixth.lmsservice.presentation.home.manage.ManageViewModel
import com.dnd.sixth.lmsservice.presentation.inviteLink.InviteLinkViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.config.ConfigViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.config.push.PushViewModel
import com.dnd.sixth.lmsservice.presentation.home.manage.student.StudentViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.config.profile.ProfileViewModel
import com.dnd.sixth.lmsservice.presentation.home.classes.config.profile.password.PasswordViewModel

import com.dnd.sixth.lmsservice.presentation.lesson.progress.ClassProgressViewModel
import com.dnd.sixth.lmsservice.presentation.login.LoginViewModel
import com.dnd.sixth.lmsservice.presentation.login.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        LoginViewModel()
    }
    viewModel{
        SignUpViewModel()
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
    viewModel {
        ConfigViewModel()
    }
    viewModel {
        ProfileViewModel()
    }
    viewModel {
       PushViewModel()
    }
    viewModel{
        InviteLinkViewModel()
    }
    viewModel {
        StudentViewModel()
    }
    viewModel {
        ManageViewModel()
    }
    viewModel {
        PasswordViewModel()
    }
}
