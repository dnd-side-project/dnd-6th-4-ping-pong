package com.dnd.sixth.lmsservice.presentation.lesson.progress


import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityClassProgressBinding
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.TimeLineAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.dnd.sixth.lmsservice.presentation.feedback.StartFeedBackActivity
import com.dnd.sixth.lmsservice.presentation.feedback.WriteFeedBackActivity
import com.google.firebase.dynamiclinks.DynamicLink
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassProgressActivity : BaseActivity<ActivityClassProgressBinding, ClassProgressViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_class_progress

    //뷰모델 추가
    override val viewModel: ClassProgressViewModel by viewModel()

    val REQUEST_CODE = 100


    //액티비티 초기화 메서드
    override fun initActivity() {
        //
        with(binding) {
            viewModel = this@ClassProgressActivity.viewModel
        }

        //어답터 추가
        with(binding) {
            recyclerviewForTimeLine.adapter = TimeLineAdapter { feedbackBtn ->
                val intent = Intent(this@ClassProgressActivity, WriteFeedBackActivity::class.java)
                startActivity(intent)
            }
            recyclerviewForTimeLine.layoutManager = LinearLayoutManager(this@ClassProgressActivity)

            progressStartFeedbackBtn.setOnClickListener {
                var intent = Intent(this@ClassProgressActivity, StartFeedBackActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }

        // 클릭리스너 추가
        with(binding) {
            progressInviteBtn.setOnClickListener(this@ClassProgressActivity)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return
            }
            binding.recyclerviewForTimeLine.visibility = VISIBLE
            binding.progressStartFeedbackBtn.visibility = GONE
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.progress_invite_btn -> invite() // 수업 초대 링크 생성
        }
    }

    private fun invite() {
        val userId = "kim1234" // Query로 사용할 유저 아이디 (Uid로 변경 가능성)
        val invitationLink = "https://lmsservice.page.link/invite" // 딥 링크 URL
        val longLink = "https://lmsservice.page.link/?link=https://pingpong.class.com&apn=com.dnd.sixth.lmsservice&id=$userId"

        FirebaseDynamicLinks.getInstance().createDynamicLink()
            .setLink(Uri.parse(invitationLink))
            .setLongLink(Uri.parse(longLink))
            .setDomainUriPrefix("pingpong.class.com")
            .setAndroidParameters(
                DynamicLink.AndroidParameters.Builder(packageName)
                    .setMinimumVersion(1)
                    .build()
            ).buildShortDynamicLink().addOnSuccessListener { shortDynamicLink ->
                showToast(shortDynamicLink.shortLink.toString())
                shortDynamicLink.shortLink?.let { sendInviteLink(it) }
            }.addOnFailureListener {
                if (BuildConfig.DEBUG) {
                    it.printStackTrace()
                }
                showToast("초대하기 기능에 실패했습니다.")
            }
    }


    private fun sendInviteLink(inviteLink: Uri) {
        val teacherName = "최기택"
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
        }catch (e: ActivityNotFoundException) {
            // 카카오톡이 설치되어 있지 않은 경우 예외 발생
            showToast("카카오톡이 설치되어 있지 않습니다.")
        }
    }

}