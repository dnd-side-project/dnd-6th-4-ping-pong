package com.dnd.sixth.lmsservice.presentation.main.classmanage.config.profile.password.method

import android.text.method.PasswordTransformationMethod
import android.view.View

class AsteriskPasswordTransformationMethod: PasswordTransformationMethod() {
    override fun getTransformation(source: CharSequence, view: View?): CharSequence {
        return PasswordCharSequence(source)
    }

    private class PasswordCharSequence(private val mSource: CharSequence) : CharSequence {

        override val length: Int
            get() = mSource?.length ?: 0 // Return default

        override fun get(index: Int): Char = '*'

        override fun subSequence(start: Int, end: Int): CharSequence {
            return mSource.subSequence(start, end)
        }
    }
}