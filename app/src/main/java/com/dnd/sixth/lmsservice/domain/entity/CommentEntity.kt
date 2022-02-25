package com.dnd.sixth.lmsservice.domain.entity

import com.google.gson.annotations.SerializedName

data class CommentEntity(

    val dailyClassId: Int,

    val dailyComment: String,

    val difficulty: Int

)
