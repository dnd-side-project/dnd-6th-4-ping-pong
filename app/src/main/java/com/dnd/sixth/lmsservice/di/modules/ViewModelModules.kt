package com.dnd.sixth.lmsservice.di.modules

import com.dnd.sixth.lmsservice.presentation.comment.CommentViewModel
import com.dnd.sixth.lmsservice.presentation.comment.check.CheckCommentViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.feedback.check.CheckFeedBackViewModel
import com.dnd.sixth.lmsservice.presentation.homework.HomeworkManageViewModel
import com.dnd.sixth.lmsservice.presentation.inviteLink.InviteLinkViewModel
import com.dnd.sixth.lmsservice.presentation.lesson.progress.ClassProgressViewModel
import com.dnd.sixth.lmsservice.presentation.login.LoginViewModel
import com.dnd.sixth.lmsservice.presentation.login.signup.SignUpViewModel
import com.dnd.sixth.lmsservice.presentation.main.MainViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.ClassManageViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.CalendarViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.ScheduleAddViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.add.push.PushTimeViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.student.edit.StudentScheduleEditViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.student.request.EditRequestViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.teacher.edit.TeacherScheduleEditViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.detail.teacher.response.EditResponseViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.SubjectViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.create.CreateSubjectViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.edit.SubjectEditViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.ConfigViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.ProfileViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.password.PasswordViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.push.PushViewModel
import com.dnd.sixth.lmsservice.presentation.main.studentmanage.StudentManageViewModel
import com.dnd.sixth.lmsservice.presentation.main.studentmanage.info.InfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel{
        SignUpViewModel()
    }
    viewModel {
        ClassManageViewModel()
    }
    viewModel {
        CreateSubjectViewModel(get(), get())
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
    viewModel {
        StartFeedBackViewModel()
    }
    viewModel {
        WriteFeedBackViewModel()
    }
    viewModel {
        MainViewModel()
    }
    viewModel {
        SubjectViewModel(get(), get(), get())
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
        InfoViewModel()
    }
    viewModel {
        StudentManageViewModel()
    }
    viewModel {
        PasswordViewModel()
    }
    viewModel {
        SubjectEditViewModel()
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
