package com.dnd.sixth.lmsservice.presentation.login.signup

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.EditText
import android.widget.Toast
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.network.api.SignApi
import com.dnd.sixth.lmsservice.data.response.UserCreateDto
import com.dnd.sixth.lmsservice.data.response.UserResponse
import com.dnd.sixth.lmsservice.databinding.ActivitySignUpBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

    //회원가입 유형 -> 추후 정리 필요
   // var checkedType : String? = null

    //회원가입 요청 시 넘겨줄 정보
    lateinit var email : String
    lateinit var userNm : String
    lateinit var password : String
    var role = 2 //선택 안 할 시 2
    var phoneNum : String = "0100101"
    var parentPhoneNum : String? = null

    //회원가입 api
    val signApi : SignApi = SignApi()



    override fun initActivity() {
        with(binding){
            viewModel = this@SignUpActivity.viewModel

            //회원가입 유형 체크박스 폼 보이기
            signupCheckboxContainer.visibility= VISIBLE

            //회원가입 레이아웃 현재 페이지
            pageNumber = 1

            initNextButton()
            initBackButton()
            initTypeCheckButton()

            //유효성 검사
            edittextListen(signupEmailEdittext, "email")
            edittextListen(signupPasswordEdittext , "password")








        }
    }
    //타입 선택 버튼 초기화
    fun initTypeCheckButton(){
        with(binding){
            signupStudentCheckbox.setOnClickListener {
                //checkedType = "Student"
                role = 0 //학생인 경우 0
                signupStudentCheckbox.setImageResource(R.drawable.ic_student_activated)
                signupTeacherCheckbox.setImageResource(R.drawable.ic_teacher_disabled)
            }
            signupTeacherCheckbox.setOnClickListener {
                //checkedType = "Teacher"
                role = 1 //선생님인 경우 1
                signupStudentCheckbox.setImageResource(R.drawable.ic_student_disabled)
                signupTeacherCheckbox.setImageResource(R.drawable.ic_teacher_activated)
            }
        }
    }

    //다음 버튼 초기화
    fun initNextButton(){
        with(binding){

            signup_next_button.setOnClickListener {
                when(pageNumber){
                    //현재 회원가입 유형 체크하고 버튼 클릭
                    1 ->{
                        if(role == 2){ //아무것도 선택안 했을 시 ==2
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
                    //이메일 입력후 버튼 클릭
                    2 -> {


                        if(emailPass){
                            pageNumber++
                            signupEmailContainer.visibility = GONE
                            signupPasswordContainer.visibility = VISIBLE
                            //입력된 이메일
                            email = signupEmailEdittext.text.toString()
                            return@setOnClickListener
                        }else{
                            Toast.makeText(applicationContext, "다시 확인해주세요.",Toast.LENGTH_LONG).show()
                        }

                    }

                    3->{ //비밀번호 입력후 버튼 클릭

                        //비밀번호 형식이 통과하였을 때
                        if(passwordPass){
                            pageNumber++
                            password = signupPasswordEdittext.text.toString()
                            signupPasswordContainer.visibility=GONE
                            signupNameContainer.visibility = VISIBLE
                            signupNextButton.text = "회원가입하기"
                            password = signupPasswordEdittext.text.toString()
                            return@setOnClickListener
                        }else{
                            Toast.makeText(applicationContext, "다시 확인해주세요.",Toast.LENGTH_LONG).show()
                        }

                    }

                    4->{//이름 입력하고 회원가입
                        //입력된 이름
                        userNm = signupNameEdittext.text.toString()
                        //val signUpData = SignUpCall(email,user_nm,password,role,"010-0101-0101",null)
                        val signUpData = UserCreateDto(email,"string","string",1,"string",null)
                        //회원가입 요청

                        signApi.api.signUp(signUpData).enqueue(object : Callback<UserResponse>{
                            override fun onResponse(
                                call: Call<UserResponse>,
                                response: Response<UserResponse>
                            ) {
                                Log.d("signUp","success")
                                return
                            }

                            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                                Log.d("signUp", "fail")
                            }

                        })








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
                    3 -> {//비밀번호 입력 화면
                        signupPasswordContainer.visibility = GONE
                        signupEmailContainer.visibility = VISIBLE
                        pageNumber--
                    }
                    4->{//이름
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
                //signupEmailEdittext.setTextColor(Color.RED) 붉은 색 처리 디자인 변경으로 주석처리
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
                //signupPasswordEdittext.setTextColor(Color.RED)
                //signupPasswordTextContainer.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_wrong_edittext))
                descriptionWrongPassword.visibility=VISIBLE
                passwordPass = false
                false
            }
        }

    }




}