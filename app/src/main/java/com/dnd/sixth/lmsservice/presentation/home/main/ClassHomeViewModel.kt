package com.dnd.sixth.lmsservice.presentation.home.main

import androidx.lifecycle.MutableLiveData
import com.dnd.sixth.lmsservice.presentation.base.BaseViewModel

class ClassHomeViewModel : BaseViewModel(){
    companion object {
        // ViewPager안에 있는 Fragment의 높이
        val screenHeight = MutableLiveData<Int>()
    }
}