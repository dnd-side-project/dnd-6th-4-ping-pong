package com.dnd.sixth.lmsservice.presentation.extensions

import android.view.View

fun List<String>.visibleIfContains(text: String, view: View) {
    if (this.contains(text)) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

fun List<String>.visibleViewListIfContain(textList: List<String>, view: List<View>) {

    for (i in 0..textList.size) {
        if (this.contains(textList[0])) {
            view[i].visibility = View.VISIBLE
        } else {
            view[i].visibility = View.GONE
        }
    }
}