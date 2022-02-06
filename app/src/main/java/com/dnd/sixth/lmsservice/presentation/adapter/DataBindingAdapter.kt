package com.dnd.sixth.lmsservice.presentation.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dnd.sixth.lmsservice.R
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("setProfileUrl")
fun CircleImageView.setProfileUrl(url: String) {
    Glide.with(context).load(Uri.parse(url)).into(this)
}

@BindingAdapter("grade", "subject")
fun TextView.setGradeSubject(grade: String, subject: String) {
    // 학생님 수업 정보
    text = context.getString(R.string.grade_subject_format, grade, subject)

    // 선생님 수업 정보
    //text = context.getString(R.string.subject_format, "영어")
}

@BindingAdapter("notificationVisible")
fun ImageView.setNotificationVisible(hasNoti: Boolean) {
    if (hasNoti) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
