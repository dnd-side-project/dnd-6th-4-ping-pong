package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.presentation.comment.CommentViewModel
import com.dnd.sixth.lmsservice.presentation.comment.check.CheckCommentViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.check.CheckFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.home.HomeViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.ClassHomeViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.HomeCalendarViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.add.ScheduleAddViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.add.push.PushTimeViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.detail.student.edit.StudentScheduleEditViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.detail.student.request.EditRequestViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.detail.teacher.edit.TeacherScheduleEditViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.calendar.detail.teacher.response.EditResponseViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.classes.ClassViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.classes.create.CreateClassViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.classes.edit.ClassEditViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.config.ConfigViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.config.profile.ProfileViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.config.profile.password.PasswordViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.config.push.PushViewModel
import com.dnd.sixth.lmsservice.presentation.home.manage.ManageViewModel
import com.dnd.sixth.lmsservice.presentation.home.manage.student.StudentViewModel
import com.dnd.sixth.lmsservice.presentation.homework.HomeworkManageViewModel
import com.dnd.sixth.lmsservice.presentation.inviteLink.InviteLinkViewModel
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
        ClassHomeViewModel()
    }
    viewModel {
        CreateClassViewModel()
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
        ClassViewModel()
    }
    viewModel {
        EditResponseViewModel()
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
        CommentViewModel()
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
    viewModel {
        ClassEditViewModel()
    }
    viewModel {
        PushTimeViewModel()
    }
    viewModel {
        StudentScheduleEditViewModel()
    }
    viewModel {
        EditRequestViewModel()
    }
    viewModel {
        TeacherScheduleEditViewModel()

    }
    viewModel{
        HomeworkManageViewModel()
    }

    viewModel{
        CheckFeedBackViewModel()
    }
    viewModel{
        CheckCommentViewModel()
    }
}
