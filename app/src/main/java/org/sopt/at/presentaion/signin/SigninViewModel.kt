package org.sopt.at.presentaion.signin

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.at.PrefsManager
import org.sopt.at.data.remote.remote.ServicePool
import org.sopt.at.data.remote.remote.dto.signin.signinrequest
import org.sopt.at.data.remote.remote.dto.signin.signinresponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState
    private var sharedPreferences: SharedPreferences? = null

    fun setSharedPreferences(sharedPreferences: SharedPreferences) {
        this.sharedPreferences = sharedPreferences
    }

    fun signIn(userId: String, password: String) {
        _loginState.value = LoginState.Loading


        val request = signinrequest(userId, password)

        ServicePool.signinAPi.signinService(request).enqueue(object : Callback<signinresponse> {
            override fun onResponse(
                call: Call<signinresponse>,
                response: Response<signinresponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val signinResponse = response.body()!!

                    if (signinResponse.success) {
                        PrefsManager.saveUserId(signinResponse.data.userId)
                        _loginState.value = LoginState.Success

                    } else {
                        _loginState.value = LoginState.Failure("아이디 또는 비밀번호가 잘못되었습니다.")
                    }
                } else {
                    _loginState.value = LoginState.Failure("서버 응답 오류")
                }
            }

            override fun onFailure(call: Call<signinresponse>, t: Throwable) {
                _loginState.value = LoginState.Failure("네트워크 오류: ${t.localizedMessage}")
            }
        })
    }

    sealed class LoginState {
        object Loading : LoginState()
        object Success : LoginState()
        data class Failure(val errorMessage: String) : LoginState()
    }

}

