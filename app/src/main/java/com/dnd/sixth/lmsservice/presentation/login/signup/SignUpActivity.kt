package com.dnd.sixth.lmsservice.presentation.login.signup

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

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

class SignUpActivity : BaseActivity<ActivitySignUpBinding, SignUpViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_sign_up

    override val viewModel: SignUpViewModel by viewModel()
    //override val viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)


    //유효성 검사를 위한 패턴
    private val emailPattern: Pattern = android.util.Patterns.EMAIL_ADDRESS
    private val passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@\$!%*#?&])[A-Za-z\\d\$@\$!%*#?&]{8,16}\$"

    var emailPass = false
    //뷰모델로 적용
    var passwordPass = false


    //뷰모델로 옮김
    var pageNumber = 1

    //회원가입 유형 -> 추후 정리 필요
    // var checkedType : String? = null

    //회원가입 요청 시 넘겨줄 정보
    lateinit var email: String
    lateinit var userNm: String
    lateinit var password: String
    var role = 2 //선택 안 할 시 2
    var phoneNum: String = "0100101"
    var parentPhoneNum: String? = null

    var preMap = mutableMapOf(
        "email" to "",
        "userNm" to "",
        "password" to "",
        "phoneNum" to "",
        "parentPhoneNum" to "")

    //회원가입 api
    val signApi: SignApi = SignApi()


    override fun initActivity() {

        viewModel.init()

        viewModel.userRole.observe(this,Observer{
            this.role =  it!!
        })

        viewModel.userInputInfo.observe(this,Observer{

            this.email = it.get("email")!!.toString()
            this.password = it.get("password")!!.toString()


            preMap["email"] = it.get("email")!!.toString()
            preMap["password"] = it.get("password")!!.toString()
        })

        viewModel.pageNumber.observe(this, Observer {
            // it 는 LiveData 로 선언된 페이지가 변경되었을 때 전달되는 값
            this.pageNumber = it
            //현재 페이지를 보여주는 함수
            showCurrentPage(it)
        })

        viewModel.passwordPass.observe(this, Observer {
            // it 는 LiveData 로 선언된 페이지가 변경되었을 때 전달되는 값
            this.passwordPass = it
            //현재 페이지를 보여주는 함수
            edittextListen(binding.signupPasswordEdittext, "password")

        })




        with(binding) {
            viewModel = this@SignUpActivity.viewModel

            //회원가입 유형 체크박스 폼 보이기
           // signupCheckboxContainer.visibility = VISIBLE

            //회원가입 레이아웃 현재 페이지
            //pageNumber = 1

            initNextButton()
            initBackButton()
            initTypeCheckButton()

            //유효성 검사
            edittextListen(signupEmailEdittext, "email")
            edittextListen(signupPasswordEdittext, "password")


        }
    }


    val pageList : MutableList<View> by lazy {

        mutableListOf(
            binding.signupCheckboxContainer,
            binding.toolbarBackBtn,
            binding.toolbarQuitBtn,
            binding.signupEmailContainer,
            binding.signupPasswordContainer,
            binding.signupNameContainer,
        )
    }

        /*
        현재 페이지를 보여주는 함수
         */
        fun showCurrentPage(page: Int) {

            when (page) {

                1 -> {//체크박스 페이지
                    with(binding) {
                        pageList.forEach { it ->
                            it.visibility = GONE
                        }
                        signupCheckboxContainer.visibility = VISIBLE //체크박스 레이아웃 보이기
                        toolbarQuitBtn.visibility = VISIBLE //나가기 버튼 활성화
                    }

                }
                2 -> { //이메일 입력 페이지
                    pageList.forEach { it ->
                        it.visibility = GONE
                    }

                    with(binding) {
                        signupEmailContainer.visibility = VISIBLE
                        toolbarBackBtn.visibility = VISIBLE
                    }
                }
                3 -> { //비밀번호 입력 페이지
                    pageList.forEach { it ->
                        it.visibility = GONE
                    }
                    with(binding) {
                        signupPasswordContainer.visibility = VISIBLE
                    }


                }
                4 -> { //이름 입력 페이지 (마지막 페이지)
                    pageList.forEach { it ->
                        it.visibility = GONE
                    }
                    with(binding) {
                        signupNameContainer.visibility = VISIBLE
                        signupNextButton.text = "회원가입하기"
                    }

                }
            }
        }

        //타입 선택 버튼 초기화
        fun initTypeCheckButton() {

                binding.signupStudentCheckbox.setOnClickListener {
                    //checkedType = "Student"
                    viewModel!!.userRole.postValue(0)//학생인 경우 0
                    binding.signupStudentCheckbox.setImageResource(R.drawable.ic_student_activated)
                    binding.signupTeacherCheckbox.setImageResource(R.drawable.ic_teacher_disabled)
                    role = 0

                }
                binding.signupTeacherCheckbox.setOnClickListener {
                    //checkedType = "Teacher"
                    //role = 1 //선생님인 경우 1
                    viewModel!!.userRole.postValue(1)
                    binding.signupStudentCheckbox.setImageResource(R.drawable.ic_student_disabled)
                    binding.signupTeacherCheckbox.setImageResource(R.drawable.ic_teacher_activated)
                    role = 1
                }
            }


        //다음 버튼 초기화
        fun initNextButton() {
            with(binding){

                signupNextButton.setOnClickListener {
                    when (pageNumber) {
                        //현재 회원가입 유형 체크하고 버튼 클릭
                        1 -> {
                            if (role == 2) { //아무것도 선택안 했을 시 ==2
                                Toast.makeText(
                                    applicationContext,
                                    "가입유형을 한가지 체크해주세요!",
                                    Toast.LENGTH_LONG
                                ).show()
                                return@setOnClickListener
                            } else {

                                viewModel!!.pageNumber.value= 2
//                            pageNumber++
//                            signupCheckboxContainer.visibility = GONE
//                            signupEmailContainer.visibility = VISIBLE
//                            toolbarQuitBtn.visibility = GONE
//                            toolbarBackBtn.visibility = VISIBLE
//                            return@setOnClickListener

                                return@setOnClickListener
                            }
                        }
                        //이메일 입력후 버튼 클릭
                        2 -> {


                            if (emailPass) {

                                //pageNumber++
//                            signupEmailContainer.visibility = GONE
//                            signupPasswordContainer.visibility = VISIBLE
                                //입력된 이메일
                                email = signupEmailEdittext.text.toString()
                                //뷰모델 업데이트
                                viewModel!!.mapUserInfo["email"] = email
                                preMap["email"] = email
                                viewModel!!.userInputInfo.postValue(preMap)
                                viewModel!!.pageNumber.value= 3
                                return@setOnClickListener

                            } else {
                                Toast.makeText(applicationContext, "다시 확인해주세요.", Toast.LENGTH_LONG)
                                    .show()
                                return@setOnClickListener
                            }

                        }

                        3 -> { //비밀번호 입력후 버튼 클릭

                            //비밀번호 형식이 통과하였을 때
                            if (passwordPass) {

                                //pageNumber++
                                password = signupPasswordEdittext.text.toString()
                                //signupPasswordContainer.visibility = GONE
                                //signupNameContainer.visibility = VISIBLE
                                //signupNextButton.text = "회원가입하기"
                                //return@setOnClickListener

                                viewModel!!.mapUserInfo["password"] = password
                                preMap["password"] = password
                                viewModel!!.userInputInfo.value = preMap
                                viewModel!!.pageNumber.value= 4
                                return@setOnClickListener
                            } else {
                                Toast.makeText(applicationContext, "다시 확인해주세요.", Toast.LENGTH_LONG)
                                    .show()
                                return@setOnClickListener
                            }

                        }

                        4 -> {//이름 입력하고 회원가입
                            //입력된 이름
                            userNm = signupNameEdittext.text.toString()
                            val signUpData = UserCreateDto(email,userNm,password,role,"",null)
                            //val signUpData = UserCreateDto(email, "string", "string", 1, "string", null)

                            //viewModel!!.mapUserInfo["userNm"] = userNm
                            //preMap["userNm"] = password
                            //viewModel!!.userInputInfo.value = preMap

                            //회원가입 요청

                            signApi.api.signUp(signUpData).enqueue(object : Callback<UserResponse> {
                                override fun onResponse(
                                    call: Call<UserResponse>,
                                    response: Response<UserResponse>
                                ) {
                                    Log.d("signUp", "success")
                                    finish()
                                    return
                                }

                                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                                    Log.d("signUp", "fail")
                                    Toast.makeText(this@SignUpActivity,"입력값을 다시 확인해주세요", Toast.LENGTH_LONG).show()
                                    viewModel?.pageNumber!!.value = 2

                                }

                            })


                        }


                    }

                }
            }
        }

        fun initBackButton() {


                binding.toolbarBackBtn.setOnClickListener {
                    when (pageNumber) {
                        2 -> {
                           // signupCheckboxContainer.visibility = VISIBLE
                           // signupEmailContainer.visibility = GONE
                           // toolbarBackBtn.visibility = GONE
                            //toolbarQuitBtn.visibility = VISIBLE
                            viewModel.pageNumber.value = 1
                        }
                        3 -> {//비밀번호 입력 화면
                           // signupPasswordContainer.visibility = GONE
                           // signupEmailContainer.visibility = VISIBLE
                            viewModel.pageNumber.value = 2
                        }
                        4 -> {//이름
                            //signupNameContainer.visibility = GONE
                            //signupPasswordContainer.visibility = VISIBLE
                            binding.signupNextButton.text = "다음"
                            viewModel.pageNumber.value = 3
                        }
                    }
                }

            binding.toolbarQuitBtn.setOnClickListener {
                    finish()
                }



        }

        //edittext 리스너 메소드
        fun edittextListen(text: EditText, code: String) {

            text.addTextChangedListener(object : TextWatcher {
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




                }

                override fun afterTextChanged(p0: Editable?) {
                    checkEmail()
                    checkPassWord()
                }
            })


        }

        //이메일 유효 체크 메소드
        fun checkEmail(): Boolean {
            with(binding) {
                val email = signupEmailEdittext.text.toString().trim()
                val pass = Pattern.matches(emailPattern.toString(), email)
                return if (pass) {
                    signupEmailEdittext.setTextColor(Color.BLACK)
                    emailPass = true
                    true
                } else {
                    //signupEmailEdittext.setTextColor(Color.RED) 붉은 색 처리 디자인 변경으로 주석처리
                    emailPass = false
                    false
                }
            }

        }

        //비밀번호 유효성 검사 메소드

        fun checkPassWord(): Boolean {
            with(binding) {
                val pw = signupPasswordEdittext.text.toString().trim()
                val pass = Pattern.matches(passwordPattern, pw)
                return if (pass) {
                    signupPasswordEdittext.setTextColor(Color.BLACK)
                    descriptionWrongPassword.visibility = INVISIBLE
                    passwordPass = true
                    true
                } else {

                    descriptionWrongPassword.visibility = VISIBLE
                    passwordPass = false
                    false
                }
            }

        }

}


