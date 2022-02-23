package com.dnd.sixth.lmsservice.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class SignUpResponseEntity (
    @PrimaryKey val id: Long,
    val parent_phone_num: String?,
    val profile_url: String?
)
