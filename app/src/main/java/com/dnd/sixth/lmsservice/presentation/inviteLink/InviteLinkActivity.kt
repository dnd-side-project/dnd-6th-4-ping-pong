package com.dnd.sixth.lmsservice.presentation.inviteLink


import android.net.Uri
import android.widget.Toast
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityInviteLinkBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseActivity
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class InviteLinkActivity : BaseActivity<ActivityInviteLinkBinding, InviteLinkViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_invite_link

    override val viewModel: InviteLinkViewModel by viewModel()

    override fun initActivity() {
        with(binding) {


            // 초대 코드 링크를 통해 들어온 것인지 확인
            checkInviteLink()
        }
    }


    private fun checkInviteLink() {
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                // Get deep link from result (may be null if no link is found)
                var deepLink: Uri? = null
                if (pendingDynamicLinkData != null) {
                    deepLink = pendingDynamicLinkData.link
                }

                if (deepLink != null &&
                    deepLink.getBooleanQueryParameter("id", false)
                ) {
                    val referrerUserId = deepLink.getQueryParameter("id")
                    Toast.makeText(this, "$referrerUserId", Toast.LENGTH_SHORT).show()
                }
            }
    }
}