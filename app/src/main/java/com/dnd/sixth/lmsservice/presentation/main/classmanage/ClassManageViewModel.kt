package com.dnd.sixth.lmsservice.presentation.main.classmanage

import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.classes.ClassFragment

class ClassManageViewModel : BaseViewModel(){
    companion object {
        // ViewPager안에 있는 Fragment의 높이
        val screenHeight = MutableLiveData<Int>()
        // 선택된 Fragment (클래스, 캘린더) 의 이름
        val selectedFragmentName = MutableLiveData<String>(ClassFragment::class.java.simpleName)
    }
}