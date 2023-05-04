package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile

import android.app.TimePickerDialog
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityProfileBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.ConfigActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.password.ChangePWActivity
import com.dnd.sixth.lmsservice.presentation.utility.ROLE_TEACHER
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
    private lateinit var changePWActivityLauncher: ActivityResultLauncher<Intent>


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

            setActivityLauncher() // 액티비티 런처를 설정합니다.
        }
    }

    private fun setActivityLauncher() {
        changePWActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                showSnackBar("비밀번호를 변경했어요!")
            }else if(result.resultCode == RESULT_CANCELED) {
                showSnackBar("비밀번호 변경에 실패했어요!")
            }
        }
    }

    private fun applyViewAsRole() {
        with(binding) {
            emailTextView.text = viewModel?.getEmail() // 이메일 적용

            // 학생인 경우
            if (viewModel?.isStudent()!!) {
                userTypeTextView.text = "학생"
                parentNumberContainer.visibility = View.VISIBLE // 학부모 연락처 입력 컨테이너를 보여줍니다.
            }
            // 선생님인 경우
            else {
                userTypeTextView.text = "과외 선생님"
                contactTimeChangeContainer.visibility = View.VISIBLE // 연락 가능 시간 컨테이너를 보여줍니다.
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
                changePWActivityLauncher.launch(Intent(this, ChangePWActivity::class.java))
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
                            if (isSaved) {
                                saveLocalContactTime() // Local에도 시간대를 저장합니다.
                                showSnackBar("연락 가능한 시간을 변경했어요!")
                            } else {
                                showSnackBar("연락 가능한 시간 변경에 실패했어요!")
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


    private fun showSaveLoadingBar() {
        binding.saveLoadingBar.visibility = View.VISIBLE
    }

    private fun hideSaveLoadingBar() {
        binding.saveLoadingBar.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> saveAndFinish() // 변경사항을 저장하고 종료합니다.
        }
        return true
    }

    override fun onBackPressed() {
        // 변경사항을 저장하고 종료합니다.
        saveAndFinish()
    }

    private fun saveAndFinish() {
        // 데이터를 서버에 전송하는 동안 Progress Bar를 보여줍니다.
        showSaveLoadingBar()

        with(viewModel) {
            CoroutineScope(Dispatchers.Default).launch {
                val parentSaveResult = withContext(Dispatchers.IO) {
                    saveLocalParentNumber() // 서버에 본인 전화번호 변경 요청
                    Log.d("dddd","ddd1")
                    saveRemoteParentNumber() // 서버에 학부모 전화번호 변경 요청
                }
                val mySaveResult = withContext(Dispatchers.IO) {
                    saveLocalMyNumber()
                    Log.d("dddd","ddd2")
                    saveRemoteMyNumber()
                }

                // UI 작업
                launch(Dispatchers.Main) {
                    // 서버에 데이터 전송을 마치면 Progress Bar를 숨깁니다.
                    hideSaveLoadingBar()

                    // 액티비티 종료
                    val isSuccess = mySaveResult || parentSaveResult
                    val resultIntent = Intent().putExtra(ConfigActivity.INTENT_CONFIG_ACTIVITY_KEY, isSuccess)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }

            }
        }
    }
}