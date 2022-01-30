package com.dnd.sixth.lmsservice.presentation.adapter.pickerview

import com.dnd.sixth.lmsservice.presentation.adapter.pickerview.item.PushPickerItem
import datepicker.defaults.view.PickerView

class PushTimePickerAdapter(private val pushTimeList: Array<String>): PickerView.Adapter<PickerView.PickerItem>() {

    override fun getItemCount(): Int {
        return pushTimeList.size
    }

    override fun getItem(index: Int): PickerView.PickerItem {
        return PushPickerItem(pushTimeList[index])
    }
}