package org.sopt.at.data.remote.remote.dto.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class signinresponse(
    @SerialName("success")
    val success : Boolean,
    @SerialName("code")
    val code :String,
    @SerialName("message")
    val message :String,
    @SerialName("data")
    val data : getId
)

@Serializable
data class getId(
    @SerialName("userId")
    val userId: Long
)
