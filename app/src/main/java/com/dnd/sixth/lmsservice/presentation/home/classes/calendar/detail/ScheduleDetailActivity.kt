package com.dnd.sixth.lmsservice.presentation.home.classes.calendar.detail

import android.content.Intent
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityScheduleDetailBinding
import com.dnd.sixth.lmsservice.databinding.FragmentEditRequestDialogBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.home.classes.calendar.detail.request.EditRequestDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScheduleDetailActivity :
    BaseActivity<ActivityScheduleDetailBinding, ScheduleDetailViewModel>(), View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_schedule_detail
    override val viewModel: ScheduleDetailViewModel by viewModel()

    private val EDIT_REQUEST_DIALOG_FRAGMENT = "EDIT_REQUEST_DIALOG_FRAGMENT"

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            setClickListener(this)
        }
    }

    private fun setClickListener(binding: ActivityScheduleDetailBinding) {
        with(binding) {
            requestEditBtn.setOnClickListener(this@ScheduleDetailActivity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.request_edit_btn -> {
                EditRequestDialogFragment().show(supportFragmentManager, EDIT_REQUEST_DIALOG_FRAGMENT)

            }
        }
    }
}