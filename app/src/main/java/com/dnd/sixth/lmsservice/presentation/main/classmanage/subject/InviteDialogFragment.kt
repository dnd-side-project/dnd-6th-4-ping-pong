package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentInviteDialogBinding
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.create.CreateSubjectViewModel
import com.dnd.sixth.lmsservice.presentation.utility.WindowCompat
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class InviteDialogFragment : DialogFragment(), View.OnClickListener {

    private lateinit var binding: FragmentInviteDialogBinding
    val viewModel: CreateSubjectViewModel by sharedViewModel()
    val subjectId: Int? by lazy {
        arguments?.getInt(SubjectFragment.INTENT_SUBJECT_ID_KEY)
    }
    val teacherName: String? by lazy {
        arguments?.getString(SubjectFragment.INTENT_TEACHER_NAME_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInviteDialogBinding.inflate(inflater, container, false) // 데이터 바인딩 객체 생성
        binding.viewModel = viewModel // 뷰모델 바인드

        // dialog 모서리를 둥글게 함
        with(dialog!!) {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)) // 투명한 배경
            window?.requestFeature(Window.FEATURE_NO_TITLE) // < 코드를 작성하지 않으면, 안드로이드 버전 4.4 이하에서 blue line이 나타남
            setCanceledOnTouchOutside(false) // 다이얼로그 바깥 화면 터치시 종료되지 않도록 설정
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            closeBtn.setOnClickListener(this@InviteDialogFragment)
            kakaoInviteBtn.setOnClickListener(this@InviteDialogFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        // 다이얼로그의 너비를 화면 너비의 0.8만큼으로 조정
        makeDialogWidthFull()
    }

    // 다이얼로그의 너비를 화면 너비의 0.8만큼으로 조정
    private fun makeDialogWidthFull() {
        val params: WindowManager.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = WindowCompat.getWindowWidth()
        params?.width = (deviceWidth * 0.8).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.close_btn -> dismiss()

            R.id.copy_btn -> {

            }
            R.id.kakao_invite_btn -> {
                subjectId?.let { invite(it) }
            }
        }
    }

    // 해당 링크로 접속한 학생은 회원가입시
    // subjectId에 해당하는 수업에 자동 가입됩니다.
    private fun invite(subjectId: Int) {
        // (Manifest에 설정한 scheme, host, path와 동일해야 함.)
        val invitationLink = "https://pingpongservice.page.link/invite?subjectId=$subjectId" // 생성할 다이나믹 링크

        val dynamicLink =
            FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(invitationLink))
                .setDomainUriPrefix("https://pingpongservice.page.link") // 파이어베이스 다이나믹 링크란에 설정한 Prefix 입력
                .setAndroidParameters(
                    DynamicLink.AndroidParameters.Builder().build()
                )
                .buildShortDynamicLink()

        dynamicLink.addOnSuccessListener { task ->
            val inviteLink = task.shortLink!!
            sendInviteLink(inviteLink)
        }

    }


    private fun sendInviteLink(inviteLink: Uri) {
        val inviteIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain" // 고정 text
            setPackage("com.kakao.talk") // 카카오톡 패키지 지정
            // 초대 코드 텍스트 지정
            putExtra(
                Intent.EXTRA_TEXT,
                "$teacherName 선생님이 수업에 초대하였습니다!\n[수업 링크] : $inviteLink"
            )
        }

        try {
            startActivity(inviteIntent) // 수업 초대를 위해 카카오톡 실행
            dismiss()
        } catch (e: ActivityNotFoundException) {
            // 카카오톡이 설치되어 있지 않은 경우 예외 발생
            dismiss()
        }
    }
}