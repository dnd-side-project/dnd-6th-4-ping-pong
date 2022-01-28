package com.dnd.sixth.lmsservice.presentation.utility

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.dnd.sixth.lmsservice.R
import com.tomergoldst.tooltips.ToolTip
import com.tomergoldst.tooltips.ToolTipsManager

class ToolTipManager {

    var toolTipsManager: ToolTipsManager? = null

    init {
        toolTipsManager = ToolTipsManager()
    }
    
    // 툴팁을 보여주는 메서드
    /*
    * @param position 위치
    * @param align 맞출 위치
    * */
    fun displayToolTip(view: View, group: ViewGroup, message: String, position: Int, align: Int) {
        toolTipsManager?.findAndDismiss(view) // 생성되어 있는 툴팁 해제

        if(message.isNotEmpty()) {
            val builder = ToolTip.Builder(view.context, view, group, message, position).apply {
                setAlign(align)
                setBackgroundColor(ContextCompat.getColor(view.context, R.color.toolTipColor))
                setGravity(ToolTip.GRAVITY_RIGHT)
                setTextAppearance(R.style.TooltipTextAppearance)
            }

            // 툴팁을 보여준다.
            toolTipsManager?.show(builder.build())
        }
    }

    // 모든 툴팁을 해제한다.
    fun dismissAll() {
        toolTipsManager?.dismissAll()
    }

}