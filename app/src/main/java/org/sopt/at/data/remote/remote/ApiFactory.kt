package org.sopt.at.data.remote.remote


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.at.BuildConfig
import org.sopt.at.data.remote.service.NicknameChangeApi
import org.sopt.at.data.remote.service.NicknameMyApi
import org.sopt.at.data.remote.service.NicknameSearchApi
import org.sopt.at.data.remote.service.SigninApi
import org.sopt.at.data.remote.service.SignupApi
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.BASE_URL

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}


object ServicePool {
    val signupApi: SignupApi by lazy {
        ApiFactory.create<SignupApi>()
    }
    val signinAPi: SigninApi by lazy{
        ApiFactory.create<SigninApi>()
    }
    val nicknameSearchApi : NicknameSearchApi by lazy{
        ApiFactory.create<NicknameSearchApi>()
    }
    val nicknameChangeApi : NicknameChangeApi by lazy{
        ApiFactory.create<NicknameChangeApi>()
    }
    val nicknameMyApi : NicknameMyApi by lazy{
        ApiFactory.create<NicknameMyApi>()
    }
}