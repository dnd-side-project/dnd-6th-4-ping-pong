package com.dnd.sixth.lmsservice.presentation.adapter.pickerview.item

import datepicker.defaults.view.PickerView

class PushPickerItem(private val itemText: String) : PickerView.PickerItem {
    override fun getText(): String {
        return itemText
    }
}