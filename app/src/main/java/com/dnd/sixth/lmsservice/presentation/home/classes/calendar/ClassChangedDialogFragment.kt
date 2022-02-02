package com.dnd.sixth.lmsservice.presentation.home.classes.calendar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassChangedDialogBinding
import com.dnd.sixth.lmsservice.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ClassChangedDialogFragment : DialogFragment(), View.OnClickListener {

    private lateinit var binding: FragmentClassChangedDialogBinding
    val viewModel: HomeViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClassChangedDialogBinding.inflate(inflater, container, false) // 데이터 바인딩 객체 생성
        binding.viewModel = viewModel // 뷰모델 바인드

        // dialog 모서리를 둥글게 함
        with(dialog!!) {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 투명한 배경
            window?.requestFeature(Window.FEATURE_NO_TITLE) // < 코드를 작성하지 않으면, 안드로이드 버전 4.4 이하에서 blue line이 나타남
            setCanceledOnTouchOutside(false) // 다이얼로그 바깥 화면 터치시 종료되지 않도록 설정
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            goHomeBtn.setOnClickListener(this@ClassChangedDialogFragment)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.go_home_btn -> dismiss()
            R.id.share_btn -> {

            }
        }
    }
}