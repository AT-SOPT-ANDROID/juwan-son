package org.sopt.at.data.remote.service

import org.sopt.at.data.remote.remote.dto.nicknamechange.nicknamechangerequest
import org.sopt.at.data.remote.remote.dto.nicknamechange.nicknamechangeresponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Query

interface NicknameChangeApi {
    @PATCH ("/api/v1/users")
    fun changeSevice(
        @Header ("userId") userId : Long,
        @Query ("nickname") nickname : String,
        @Body request : nicknamechangerequest

    ):Call<nicknamechangeresponse>
}