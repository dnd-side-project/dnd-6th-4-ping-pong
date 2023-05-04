package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.password

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityChangePwBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.password.method.AsteriskPasswordTransformationMethod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

class ChangePWActivity : BaseActivity<ActivityChangePwBinding, PasswordViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_change_pw
    override val viewModel: PasswordViewModel by viewModel()

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

            with(passwordEditText){
                addTextChangedListener(passwordTextWatcher) // 패스워드 TextWatcher
                transformationMethod = AsteriskPasswordTransformationMethod() // 패스워드 Mask 를 *로 표시
                setOnFocusChangeListener { _, hasFocus ->
                    // 클릭시(포커스를 가지게 되면)
                    if (hasFocus) {
                        // 힌트를 지워서 별표시 제거
                        passwordEditText.hint = ""
                    }
                }
            }
            passwordInputLayout.setEndIconOnClickListener {
                viewModel?.toggleEndIcon() // EndIcon 체크 상태 토글

                if(viewModel?.isCheckedEndIcon!!) { // 비밀번호 숨김(Hide) 상태
                    passwordEditText.transformationMethod = AsteriskPasswordTransformationMethod() // 패스워드 Mask 를 *로 표시
                }else { // 비밀번호 표시(Show) 상태
                    passwordEditText.transformationMethod = null
                }
            }

            // 비밀번호 변경 버튼 리스너
            doneBtn.setOnClickListener(this@ChangePWActivity)
        }
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.done_btn -> {
                CoroutineScope(Dispatchers.IO).launch {
                    // 비밀번호를 변경합니다.
                    val isChanged = viewModel.changePassword()
                    withContext(Dispatchers.Main) {
                        if (isChanged) {
                            setResult(RESULT_OK)
                            finish()
                        } else {
                            setResult(RESULT_CANCELED)
                            finish()
                        }
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

    // 패스워드 TextWatcher
    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(
            s: CharSequence?,
            start: Int,
            count: Int,
            after: Int
        ) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val password = binding.passwordEditText
            if (password.length() == 0) { // 아무것도 입력하지 않았다면 설명을 보여주지 않는다.
                binding.pwDescTextView.visibility = View.GONE // 비밀번호 조건을 가림
            } else { // 무언가 입력했다면
                binding.pwDescTextView.visibility = View.VISIBLE // 비밀번호 조건을 보여줌
                binding.doneBtn.isEnabled =
                    isRegularPW(password.text.toString()) // 정규식 조건에 해당한다면 확인 버튼 활성화
            }
        }

        override fun afterTextChanged(s: Editable?) {}

    }

    private fun isRegularPW(password: String): Boolean {
        val pwPattern1 =
            "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}$" // 영문, 숫자
        val pwPattern2 =
            "^(?=.*[0-9])(?=.*[$@$!%*#?&.])[[0-9]$@$!%*#?&.]{8,20}$" // 숫자, 특수문자
        val pwPattern3 =
            "^(?=.*[A-Za-z])(?=.*[$@$!%*#?&.])[A-Za-z$@$!%*#?&.]{8,20}$" // 영문, 특수문자
        val pwPattern4 =
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$" // 영문, 숫자, 특수문자

        val pattern = Pattern.compile(pwPattern1)
        val matcher = pattern.matcher(pwPattern1)
        Log.d("Match", matcher.find().toString())

        return (Pattern.matches(pwPattern1, password) ||
                Pattern.matches(pwPattern2, password) ||
                Pattern.matches(pwPattern3, password) ||
                Pattern.matches(pwPattern4, password))
    }
}