package com.dnd.sixth.lmsservice.data.response

import com.dnd.sixth.lmsservice.data.model.SignUpResponseEntity
import com.google.gson.annotations.SerializedName
import java.util.*

data class User(
    var id : Int,
    var parent_phone_num : Boolean
){
    //함수를 추가하여
    //Entity로 바꿀 수 있는 형태로 만들어줌.
    fun toEntity(): SignUpResponseEntity =
        SignUpResponseEntity(
            id = id.toLong(),
            parent_phone_num = null,

        )
}
