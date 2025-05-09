package org.sopt.at.data.remote.service

import org.sopt.at.data.remote.remote.dto.signin.signinrequest
import org.sopt.at.data.remote.remote.dto.signin.signinresponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SigninApi {
    @POST ("api/v1/auth/signin")
    fun signinService(
        @Body request : signinrequest
    ): Call<signinresponse>
}