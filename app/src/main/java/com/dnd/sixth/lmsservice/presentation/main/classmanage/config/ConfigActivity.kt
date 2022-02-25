package com.dnd.sixth.lmsservice.presentation.main.classmanage.config

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityConfigBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.ProfileActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.push.PushActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigActivity : BaseActivity<ActivityConfigBinding, ConfigViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_config
    override val viewModel: ConfigViewModel by viewModel()
    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    companion object {
        const val INTENT_CONFIG_ACTIVITY_KEY = "INTENT_CONFIG_ACTIVITY_KEY"
        const val INTENT_CONFIG_ACTIVITY_CODE = 4000
    }

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기 버튼
            supportActionBar?.setDisplayShowTitleEnabled(false) // 타이틀 보이지 않도록 설정

            setClickListener() // 클릭 리스너 설정
            setResultLauncher() // ActivityResult 설정

            nicknameEditText.setOnFocusChangeListener { v, hasFocus ->
                // 닉네임 변경 EditText가 포커스를 받았다면 텍스트 색상과 아이콘을 변경합니다.
                if (hasFocus) {
                    editIcon.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this@ConfigActivity,
                            R.color.grayC4
                        )
                    )
                    nicknameEditText.setTextColor(
                        ContextCompat.getColor(
                            this@ConfigActivity,
                            R.color.grayC4
                        )
                    )
                } else {
                    editIcon.imageTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this@ConfigActivity,
                            R.color.richBlack
                        )
                    )
                    nicknameEditText.setTextColor(
                        ContextCompat.getColor(
                            this@ConfigActivity,
                            R.color.richBlack
                        )
                    )
                }
            }
        }
    }

    private fun setResultLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val resultData = result.data
                if (result.resultCode == INTENT_CONFIG_ACTIVITY_CODE) {
                    if (resultData?.getBooleanExtra(INTENT_CONFIG_ACTIVITY_KEY, false)!!) {
                        showSnackBar("변경사항이 저장됐어요!")
                    }
                }
            }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    private fun setClickListener() {
        with(binding) {
            optionAccountBtn.setOnClickListener(this@ConfigActivity)
            optionNotificationBtn.setOnClickListener(this@ConfigActivity)
            editIcon.setOnClickListener(this@ConfigActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option_account_btn -> {
                activityResultLauncher.launch(Intent(this, ProfileActivity::class.java))
            }
            R.id.option_notification_btn -> {
                startActivity(Intent(this, PushActivity::class.java))
            }
            R.id.edit_icon -> {
                // 닉네임 편집 아이콘을 누르면 EditText에 포커스를 준다.
                binding.nicknameEditText.also {
                    if (it.hasFocus()) { // 포커스를 가지고 있다면
                        // 연필 아이콘을 누르면 닉네임을 변경합니다.
                        CoroutineScope(Dispatchers.IO).launch {
                            val isChanged = viewModel.changeUserName()
                            if (isChanged) {
                                showSnackBar("닉네임이 변경되었어요!")
                            } else {
                                showSnackBar("닉네임 변경에 실패했어요!")
                            }
                        }
                    } else { // 포커스를 가지고 있지 않다면
                        it.isFocusableInTouchMode = true // 터치로 포커스를 가능하게 변경합니다.
                        it.requestFocus() // EditText에 포커스를 둡니다.
                        it.setSelection(it.text.length) // 커서를 맨 오른쪽에 배치시킵니다.
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(it, 0) // 키보드를 띄웁니다.
                    }
                }

            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }

}