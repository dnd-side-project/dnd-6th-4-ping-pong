package com.dnd.sixth.lmsservice.data.response


data class UserResponse(

    var id : Int,
    var email : String,
    var user_nm : String,
    var password : String,
    var role : Int,
    var parent_phone_num : String,
    // var profile_url :String,

)
