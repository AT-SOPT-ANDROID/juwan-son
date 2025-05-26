package org.sopt.at.data.remote.remote.dto.nicknamechange

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class nicknamechangerequest(
    @SerialName("nickname")
    val nickname :String
)
