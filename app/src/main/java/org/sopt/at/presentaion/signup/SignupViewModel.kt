package org.sopt.at.presentaion.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.at.data.remote.remote.ServicePool
import org.sopt.at.data.remote.remote.dto.signup.signuprequest
import org.sopt.at.data.remote.remote.dto.signup.signupresponse
import org.sopt.at.domain.repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {
    private val userRepository = UserRepository.getInstance()

    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> get() = _signUpState

    fun validateId(id: String): Boolean {
        return id.length in 6..12 && id.matches("^[a-z0-9]+$".toRegex()) // 간단한 예시 유효성 검사
    }

    fun validatePassword(password: String): Boolean {
        return password.length in 8..15 &&
                password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,15}$".toRegex())
    }

    fun validateNickname(nickname: String): Boolean {
        return nickname.length >= 2
    }


    fun signUp(id: String, password: String, nickname :String) {
        _signUpState.value = SignUpState.Loading

        if (validateId(id) && validatePassword(password) && validateNickname(nickname)) {
            val request = signuprequest(id, password, nickname)

            val call = ServicePool.signupApi.SignupService(request)

            call.enqueue(object : Callback<signupresponse> {
                override fun onResponse(call: Call<signupresponse>, response: Response<signupresponse>) {
                    if (response.isSuccessful) {
                        _signUpState.postValue(SignUpState.Success)
                    } else {
                        _signUpState.postValue(SignUpState.Failure("서버 응답 오류: ${response.message()}"))
                    }
                }

                override fun onFailure(call: Call<signupresponse>, t: Throwable) {
                    _signUpState.postValue(SignUpState.Failure("네트워크 오류: ${t.localizedMessage}"))
                }
            })
        } else {

        }
    }

    sealed class SignUpState {
        object Loading : SignUpState()
        object Success : SignUpState()
        data class Failure(val errorMessage: String) : SignUpState()
    }
}