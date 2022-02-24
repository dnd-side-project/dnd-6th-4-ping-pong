package com.dnd.sixth.lmsservice.presentation.adapter

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import com.bumptech.glide.Glide
import com.dnd.sixth.lmsservice.App
import com.dnd.sixth.lmsservice.R
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("setProfileUrl")
fun setProfileUrl(circularImageView: CircleImageView, url: String?) {
    if (url != null) {
        App.instance.context.let {
            Glide.with(it).load(Uri.parse(url)).into(circularImageView)
        }
    } else {
        App.instance.context.let {
            Glide.with(it).load(R.drawable.ic_profile_img).into(circularImageView)
        }
    }
}


@BindingAdapter("notificationVisible")
fun setNotificationVisible(imageView: ImageView, hasNoti: Boolean) {
    if (hasNoti) {
        imageView.visibility = View.VISIBLE
    } else {
        imageView.visibility = View.GONE
    }
}

// String으로 받은 EditText의 값을 Integer로 변환
object Converter {
    @InverseMethod("toInt")
    @JvmStatic
    fun toString(
        value: Int
    ): String {
        // 입력받은 값이 1보다 작으면
        return if (value < 1) {
            // Default 값인 1 반환
            "1"
        } else {
            value.toString()
        }
    }

    @JvmStatic
    fun toInt(
        value: String?
    ): Int {
        // 입력받은 값이 Null이거나 비어있으면, 또는 1보다 작은 수인 경우 1을 반환한다
        return if (value.isNullOrEmpty() || Integer.parseInt(value) < 1) {
            // Default 값인 1 반환
            1
        } else {
            Integer.parseInt(value)
        }
    }
}