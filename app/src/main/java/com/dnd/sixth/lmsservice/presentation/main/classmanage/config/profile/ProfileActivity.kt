package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile

import android.app.TimePickerDialog
import android.content.Intent
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.databinding.ActivityProfileBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.password.ChangePWActivity
import com.dnd.sixth.lmsservice.presentation.utility.ROLE_TEACHER
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_ID_KEY
import com.dnd.sixth.lmsservice.presentation.utility.SAVED_ROLE_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_profile
    override val viewModel: ProfileViewModel by viewModel()

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData()
        initView()
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }


    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼
            supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보이지 않도록 설정

            setOnClickListener(this) // 클릭 리스너를 설정합니다.
            applyViewAsRole() // 선생님 / 학생에 따른 View 차이를 적용합니다.

            viewModel?.originContactTime?.observe(this@ProfileActivity) {
                contactTimeTextView.text = it
            }
        }
    }

    private fun applyViewAsRole() {
        val preferenceManager = PreferenceManager(this)
        with(binding) {
            emailTextView.text = preferenceManager.getString(SAVED_ID_KEY) // 이메일 적용

            // 선생님인 경우
            if (preferenceManager.getInt(SAVED_ROLE_KEY) == ROLE_TEACHER) {
                userTypeTextView.text = "과외 선생님"
                contactTimeChangeContainer.visibility = View.VISIBLE // 학부모 연락처 입력 컨테이너를 보여줍니다.
            }
            // 학생인 경우
            else {
                userTypeTextView.text = "학생"
                parentNumberContainer.visibility = View.VISIBLE // 학부모 연락처 입력 컨테이너를 보여줍니다.
            }
        }
    }

    private fun setOnClickListener(binding: ActivityProfileBinding) {
        with(binding) {
            passwordChangeBtn.setOnClickListener(this@ProfileActivity)
            passwordTitle.setOnClickListener(this@ProfileActivity)
            contactTimeChangeContainer.setOnClickListener(this@ProfileActivity)
            contactTimeChangeBtn.setOnClickListener(this@ProfileActivity)
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.password_change_btn, R.id.password_title -> {
                startActivity(Intent(this, ChangePWActivity::class.java))
            }
            R.id.contact_time_change_btn, R.id.contact_time_change_container -> {
                showStartTimePicker() // 연락가능 시간대를 설정할 수 있는 타임피커를 보여줍니다.
            }
        }
    }

    // 연락가능 시작 시간을 설정할 수 있는 타임피커를 보여줍니다.
    private fun showStartTimePicker() {
        val dialog = TimePickerDialog(
            this,
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
            { _, newHourOfDay, newMinute ->
                viewModel.startHour = newHourOfDay
                viewModel.startMin = newMinute
                // 연락가능 끝나는 시간대를 설정할 수 있는 타임피커를 보여줍니다.
                showEndTimePicker()
            },
            viewModel.startHour,
            viewModel.startMin,
            true
        )
        dialog.setTitle("시작 시간")
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    // 연락가능 끝나는 시간을 설정할 수 있는 타임피커를 보여줍니다.
    private fun showEndTimePicker() {
        val dialog = TimePickerDialog(
            this,
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
            { _, newHourOfDay, newMinute ->
                with(viewModel) {
                    endHour = newHourOfDay
                    endMin = newMinute
                    binding.contactTimeTextView.text = getString(
                        R.string.contact_time_format,
                        startHour, startMin, endHour, endMin
                    )
                    // 선택한 시간대를 서버 DB에 저장합니다.
                    CoroutineScope(Dispatchers.IO).launch {
                        val isSaved = viewModel.saveRemoteContactTime()
                        withContext(Dispatchers.Main) {
                            if(isSaved) {
                                saveLocalContactTime() // Local에도 시간대를 저장합니다.
                                showSnackBar("변경되었어요!")
                            } else {
                                showSnackBar("변경에 실패했어요!")
                            }
                        }
                    }
                }

            },
            viewModel.endHour,
            viewModel.endMin,
            true
        )
        dialog.setTitle("시작 시간")
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }
}