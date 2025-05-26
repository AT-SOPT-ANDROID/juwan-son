package org.sopt.at.data.remote.remote.dto.signin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class signinrequest(
    @SerialName("loginId")
    val loginId : String,
    @SerialName("password")
    val password : String
)
