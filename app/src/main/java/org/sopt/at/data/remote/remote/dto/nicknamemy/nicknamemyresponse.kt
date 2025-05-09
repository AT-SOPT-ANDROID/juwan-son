package org.sopt.at.data.remote.remote.dto.nicknamemy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class nicknamemyresponse(
    @SerialName("success")
    val success : Boolean,
    @SerialName("code")
    val code : String,
    @SerialName("message")
    val message : String,
    @SerialName("data")
    val data : myname
)
@Serializable
data class myname(
    @SerialName("nickname")
    val nickname :String
)
