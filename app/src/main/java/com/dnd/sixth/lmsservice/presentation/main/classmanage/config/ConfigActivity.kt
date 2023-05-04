package com.dnd.sixth.lmsservice.presentation.main.classmanage.config

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityConfigBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.ProfileActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.push.PushActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfigActivity : BaseActivity<ActivityConfigBinding, ConfigViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_config
    override val viewModel: ConfigViewModel by viewModel()
    private lateinit var configResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var galleryResultLauncher: ActivityResultLauncher<Intent>

    companion object {
        const val INTENT_CONFIG_ACTIVITY_KEY = "INTENT_CONFIG_ACTIVITY_KEY"
        const val INTENT_GALLERY_ACTIVITY_KEY = "INTENT_GALLERY_ACTIVITY_KEY"
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

            nicknameEditText.setOnFocusChangeListener { editText, hasFocus ->
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

                    // EditText에 포커스가 주어지면 키보드를 띄웁니다.
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .showSoftInput(editText, 0)
                } else {
                    // EditText에 포커스가 사라지면 키보드를 내립니다.
                    (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(editText.windowToken, 0)

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
        configResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val resultData = result.data
                if (result.resultCode == RESULT_OK) {
                    if (resultData?.getBooleanExtra(INTENT_CONFIG_ACTIVITY_KEY, false)!!) {
                        showSnackBar("변경사항이 저장됐어요!")
                    }
                }
            }

        galleryResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val resultIntent = result.data
                    CoroutineScope(Dispatchers.IO).launch {
                        // 프로필을 서버에 저장합니다.
                        val isUpdated = viewModel.updateProfileUri(resultIntent?.data)

                        // 성공적으로 프로필을 변경했다면, 프로필 화면을 갱신합니다.
                        withContext(Dispatchers.Main) {
                            if (isUpdated) {
                                showSnackBar("프로필이 변경되었어요!")
                                applyProfile(resultIntent?.data)
                            } else {
                                showSnackBar("프로필이 변경되지 않았어요!")
                            }
                        }
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
            profileImageView.setOnClickListener(this@ConfigActivity)
            editIcon.setOnClickListener(this@ConfigActivity)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.option_account_btn -> {
                // 계정 설정 화면을 실행합니다.
                configResultLauncher.launch(Intent(this, ProfileActivity::class.java))
            }
            R.id.option_notification_btn -> {
                // 알림 설정 화면을 실행합니다.
                startActivity(Intent(this, PushActivity::class.java))
            }
            R.id.profile_image_view -> {
                // 갤러리를 실행하고 프로필을 변경합니다.
                galleryResultLauncher.launch(Intent().apply {
                    type = "image/*"
                    action = Intent.ACTION_GET_CONTENT
                })
            }
            R.id.edit_icon -> {
                // 닉네임 편집 아이콘을 누르면 EditText에 포커스를 준다.
                binding.nicknameEditText.also {
                    if (it.hasFocus()) { // 포커스를 가지고 있다면
                        // 연필 아이콘을 누르면 닉네임을 변경합니다.
                        CoroutineScope(Dispatchers.IO).launch {
                            val isChanged = viewModel.changeUserName()
                            launch(Dispatchers.Main) {
                                if (isChanged) {
                                    showSnackBar("닉네임이 변경되었어요!")
                                    it.clearFocus() // 포커스를 해제합니다.
                                } else {
                                    showSnackBar("닉네임 변경에 실패했어요!")
                                }

                            }
                        }
                    } else { // 포커스를 가지고 있지 않다면
                        it.isFocusableInTouchMode = true // 포커스를 True로 변경합니다.
                        it.requestFocus() // Focus 변경 상태를 반영합니다.
                        it.setSelection(it.text.length) // 커서를 맨 오른쪽에 배치시킵니다.
                    }
                }

            }
        }
    }

    private fun applyProfile(uri: Uri?) {
        Glide.with(this)
            .load(uri)
            .placeholder(R.drawable.ic_profile_img)
            .error(R.drawable.ic_profile_img)
            .into(binding.profileImageView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }

        return true
    }

}