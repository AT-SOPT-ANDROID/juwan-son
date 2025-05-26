package org.sopt.at.data.remote.service

import org.sopt.at.data.remote.remote.dto.signup.signuprequest
import org.sopt.at.data.remote.remote.dto.signup.signupresponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupApi {
        @POST("api/v1/auth/signup")
        fun SignupService(
            @Body requestBody: signuprequest
        ): Call<signupresponse>
}