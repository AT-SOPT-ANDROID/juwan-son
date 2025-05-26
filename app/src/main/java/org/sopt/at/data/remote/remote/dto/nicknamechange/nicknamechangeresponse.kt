package org.sopt.at.data.remote.remote.dto.nicknamechange

import androidx.annotation.Nullable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class nicknamechangeresponse(
    @SerialName ("success")
    val success :Boolean,
    @SerialName("code")
    val code : String,
    @SerialName("message")
    val message : String,
    @SerialName("data")
    val data : List<UserNickname>
)
@Serializable
data class UserNickname(
    @SerialName("userId")
    val userId : Long,
    @SerialName("nickname")
    val nickname :String
)