package com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.notipicker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.DialogPushTimePickerBinding
import com.dnd.sixth.lmsservice.presentation.adapter.pickerview.PushTimePickerAdapter
import com.dnd.sixth.lmsservice.presentation.utility.WindowCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*


class PushTimePickerDialogFragment : BottomSheetDialogFragment(),
    View.OnClickListener {
    val layoutResId: Int
        get() = R.layout.dialog_push_time_picker

    lateinit var binding: DialogPushTimePickerBinding
    val viewModel: PushTimePickerViewModel by lazy {
        ViewModelProvider(this)[PushTimePickerViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogPushTimePickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialogSetting() // Dialog 상태 설정
        initFragment() // Dialog Fragment 초기화
    }

    // Dialog 속성 설정
    private fun dialogSetting() {
        dialog?.let {
            val sheetDialog = it as BottomSheetDialog

            with(sheetDialog.behavior) {
                state = BottomSheetBehavior.STATE_EXPANDED // 최초 실행시 반만 펼쳐지게 설정
                isHideable = false // 아래로 드래그해도 다이얼로그 유지
                skipCollapsed = true // 아래로 드래그시 반접힘 상태 스킵
                isFitToContents = true // 다이얼로그의 크기를 뷰 크기에 맞춤
            }
        }
    }

    // 액티비티 초기화 메서드
    private fun initFragment() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }


    private fun initView() {
        with(binding) {
            setListener(this) // 리스너 설정
            setNotiTimePicker(this) // 노티피케이션 알림 시간대 설정
        }
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return WindowCompat.getWindowHeight()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cancel_btn -> dismiss()
        }
    }

    private fun setListener(binding: DialogPushTimePickerBinding) {
        with(binding) {
            cancelBtn.setOnClickListener(this@PushTimePickerDialogFragment)
        }
    }

    private fun setNotiTimePicker(binding: DialogPushTimePickerBinding) {
        // 알림 시간대 설정을 위한 Picker 어댑터 설정
        with(binding.pushTimePicker) {
            setAdapter(PushTimePickerAdapter(viewModel.pushTimeList))
            setOnSelectedItemChangedListener { _, _, selectedItemPosition ->
                // Picker 아이템 변경시 pickedNotificationTime 값 업데이트
                viewModel.pickNotificationTime(selectedItemPosition)
            }
        }

    }


}