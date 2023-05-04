package com.dnd.sixth.lmsservice.presentation.listner

import androidx.annotation.IdRes

interface OnRecyclerItemClickListener {
    fun onClick(@IdRes resId: Int, position: Int = -1)
}