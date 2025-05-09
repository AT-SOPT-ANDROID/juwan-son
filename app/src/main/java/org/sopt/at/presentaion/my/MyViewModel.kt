package org.sopt.at.presentaion.my

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import org.sopt.at.PrefsManager
import org.sopt.at.data.remote.remote.ServicePool
import org.sopt.at.data.remote.remote.dto.nicknamemy.nicknamemyresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel(application: Application) : AndroidViewModel(application) {

    val userNickname = MutableLiveData<String>()

    fun fetchUserNickname() {
        val userId = PrefsManager.getUserId()
        Log.d("MyViewModel", "Retrieved userId: $userId")

        if (userId != -1L) {
            Log.d("MyViewModel", "Calling API with userId: $userId")

            ServicePool.nicknameMyApi.myservice(userId).enqueue(object : Callback<nicknamemyresponse> {
                override fun onResponse(
                    call: Call<nicknamemyresponse>,
                    response: Response<nicknamemyresponse>
                ) {
                    if (response.isSuccessful) {
                        val nickname = response.body()?.data?.nickname
                        userNickname.value = nickname ?: "닉네임 없음"
                    } else {
                        userNickname.value = "오류 발생"
                    }
                }

                override fun onFailure(call: Call<nicknamemyresponse>, t: Throwable) {
                    userNickname.value = "네트워크 오류"
                }
            })
        } else {
            userNickname.value = "로그인되지 않음"
            Log.d("MyViewModel", "No userId found in SharedPreferences")
        }
    }
}
