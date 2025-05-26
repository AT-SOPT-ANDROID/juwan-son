package org.sopt.at.data.remote.service

import org.sopt.at.data.remote.remote.dto.nicknamesearch.nicknamesearchresponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NicknameSearchApi {
    @GET("/api/v1/users")
    fun searchservice(
        @Query ("keyword") keyword : String
    ):Call<nicknamesearchresponse>
}