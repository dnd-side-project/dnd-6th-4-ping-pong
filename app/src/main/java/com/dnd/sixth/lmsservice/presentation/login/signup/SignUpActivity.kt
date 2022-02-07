package com.dnd.sixth.lmsservice.presentation.login.signup

import android.graphics.Color
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivitySignUpBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.regex.Pattern

class SignUpActivity : BaseActivity<ActivitySignUpBinding,SignUpViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_sign_up

    override val viewModel: SignUpViewModel by viewModel()

    //유효성 검사를 위한 패턴
    private val emailPattern : Pattern = android.util.Patterns.EMAIL_ADDRESS
    private val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@\$!%*#?&])[A-Za-z\\d\$@\$!%*#?&]{8,16}\$"

    var emailPass = false
    var passwordPass = false

    var pageNumber = 1


    override fun initActivity() {
        with(binding){
            viewModel = this@SignUpActivity.viewModel

            //회원가입 유형 체크박스 폼 보이기
            signupCheckboxContainer.visibility= VISIBLE

            //회원가입 레이아웃 현재 페이지
            pageNumber = 1

            initNextButton()
            initBackButton()

            //유효성 검사
            edittextListen(signupEmailEdittext, "email")
            edittextListen(signupPasswordEdittext , "password")








        }
    }

    //다음 버튼 초기화
    fun initNextButton(){
        with(binding){

            signup_next_button.setOnClickListener {
                when(pageNumber){
                    //현재 회원가입 유형 체크일 때
                    1 ->{
                        if(signupStudentCheckbox.isChecked==false&&signupTeacherCheckbox.isChecked==false||
                            signupStudentCheckbox.isChecked==true&&signupTeacherCheckbox.isChecked==true){
                            Toast.makeText(applicationContext, "가입유형을 한가지 체크해주세요!",Toast.LENGTH_LONG).show()
                            return@setOnClickListener
                        }else{
                            pageNumber++
                            signupCheckboxContainer.visibility =GONE
                            signupEmailContainer.visibility = VISIBLE
                            toolbarQuitBtn.visibility= GONE
                            toolbarBackBtn.visibility= VISIBLE
                            return@setOnClickListener
                        }
                    }
                    //이메일 입력중일 때
                    2 -> {


                        if(emailPass){
                            pageNumber++
                            signupEmailContainer.visibility = GONE
                            signupPasswordContainer.visibility = VISIBLE
                            return@setOnClickListener
                        }else{
                            Toast.makeText(applicationContext, "다시 확인해주세요.",Toast.LENGTH_LONG).show()
                        }

                    }

                    3->{


                        if(passwordPass){
                            pageNumber++
                            signupPasswordContainer.visibility=GONE
                            signupNameContainer.visibility = VISIBLE
                            signupNextButton.text = "회원가입하기"
                            return@setOnClickListener
                        }else{
                            Toast.makeText(applicationContext, "다시 확인해주세요.",Toast.LENGTH_LONG).show()
                        }

                    }

                    4->{


                    }


                }
                pageNumber++
            }
        }
    }

    fun initBackButton(){

        with(binding){
            toolbarBackBtn.setOnClickListener {
                when(pageNumber){
                    2 -> {
                        signupCheckboxContainer.visibility = VISIBLE
                        signupEmailContainer.visibility = GONE
                        toolbarBackBtn.visibility = GONE
                        toolbarQuitBtn.visibility = VISIBLE
                        pageNumber--
                    }
                    3 -> {
                        signupPasswordContainer.visibility = GONE
                        signupEmailContainer.visibility = VISIBLE
                        pageNumber--
                    }
                    4->{
                        signupNameContainer.visibility = GONE
                        signupPasswordContainer.visibility = VISIBLE
                        signupNextButton.text = "다음"
                        pageNumber--
                    }
                }
            }

            toolbarQuitBtn.setOnClickListener {
                finish()
            }

            
        }
    }

    //edittext 리스너 메소
    fun edittextListen(text : EditText, code : String){

            text.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int
                ) {

                }
                override fun onTextChanged(
                    p0: CharSequence?,
                    p1: Int,
                    p2: Int,
                    p3: Int
                ) {
                    if(code == "email"){
                        checkEmail()
                    }else if(code =="password"){
                        checkPassWord()
                    }


                }
                override fun afterTextChanged(p0: Editable?) {
                   
                }
            })


    }
    //이메일 유효 체크 메소드
    fun checkEmail() : Boolean {
        with(binding){
            val email = signupEmailEdittext.text.toString().trim()
            val pass = Pattern.matches(emailPattern.toString(), email)
            return if(pass){
                signupEmailEdittext.setTextColor(Color.BLACK)
                emailPass = true
                true
            }else{
                signupEmailEdittext.setTextColor(Color.RED)
                emailPass = false
                false
            }
        }

    }

    //비밀번호 유효성 검사 메소드

    fun checkPassWord() : Boolean {
        with(binding){
            val pw = signupPasswordEdittext.text.toString().trim()
            val pass = Pattern.matches(passwordPattern,pw )
            return if(pass){
                signupPasswordEdittext.setTextColor(Color.BLACK)
                passwordPass = true
                true
            }else{
                signupPasswordEdittext.setTextColor(Color.RED)
                signupPasswordTextContainer.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_wrong_edittext))
                descriptionWrongPassword.visibility=VISIBLE
                passwordPass = false
                false
            }
        }

    }




}