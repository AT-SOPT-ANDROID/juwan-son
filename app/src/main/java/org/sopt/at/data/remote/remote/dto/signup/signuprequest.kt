package org.sopt.at.data.remote.remote.dto.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class signuprequest(
    @SerialName("loginId")
    val loginId : String,
    @SerialName("password")
    val password : String,
    @SerialName("nickname")
    val nickname:String

)
