package org.sopt.at.data.remote.remote.dto.signup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class signupresponse(
    @SerialName("success")
    val succuess :Boolean,
    @SerialName("code")
    val code : String,
    @SerialName("message")
    val message :String,
    @SerialName("data")
    val data : List<successresponse>
)

@Serializable
data class successresponse(
    @SerialName("userId")
    val userId : Long,
    @SerialName("nickname")
    val nickname : String

)

