package org.sopt.at.data.remote.service

import org.sopt.at.data.remote.remote.dto.nicknamemy.nicknamemyresponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface NicknameMyApi {
    @GET ("/api/v1/users/me")
    fun myservice(
        @Header ("userId") userId : Long
    ):Call<nicknamemyresponse>


}