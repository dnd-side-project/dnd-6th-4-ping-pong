package com.dnd.sixth.lmsservice.presentation.utility


import android.text.Spanned

import android.text.InputFilter
import androidx.core.text.isDigitsOnly

/*
* EditText Filter : 숫자만 입력받을 수 있음
*  */
class CustomInputFilter : InputFilter {
    override fun filter(
        source: CharSequence, start: Int,
        end: Int, dest: Spanned, dstart: Int, dend: Int
    ): CharSequence {
        return if (source.isNotBlank() && source.isDigitsOnly()){
            source
        }else {
            ""
        }
    }
}