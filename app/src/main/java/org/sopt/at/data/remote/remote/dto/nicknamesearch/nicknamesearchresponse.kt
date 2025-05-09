package org.sopt.at.data.remote.remote.dto.nicknamesearch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class nicknamesearchresponse(
    @SerialName("success")
    val success :Boolean,
    @SerialName("code")
    val code :String,
    @SerialName("message")
    val message :String,
    @SerialName("data")
    val data : nicknamesucess
)

@Serializable
data class nicknamesucess(
    @SerialName("nicknameList")
    val nicknameList : List<String>
)