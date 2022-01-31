package com.dnd.sixth.lmsservice.presentation.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dnd.sixth.lmsservice.presentation.communication.CommunicationMainActivity
import com.dnd.sixth.lmsservice.presentation.communication.home.CommunicationHomeFragment
import com.dnd.sixth.lmsservice.presentation.communication.noticeAndProposal.CommunicationFragment
import com.dnd.sixth.lmsservice.presentation.main.home.HomeFragment
import com.dnd.sixth.lmsservice.presentation.main.schedule.calendar.CalendarFragment

class CommunicationPagerAdapter (activity: CommunicationMainActivity) : FragmentStateAdapter(activity) {

    // ViewPager2에 연결할 Fragment 생성
    //홈화면, 소통하기(공지,건의),
    private val fragmentList = listOf<Fragment>(CommunicationHomeFragment(), CommunicationFragment(), HomeFragment())

    // ViesPager2에서 노출시킬 Fragment 개수 설정
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // ViewPager2의 각 페이지에서 노출할 Fragment 설정
    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}