package org.sopt.at.presentaion.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.data.remote.model.User
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.presentaion.signin.SignInViewModel

class SignupViewModel : ViewModel() {
    private val userRepository = UserRepository.getInstance()

    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState> get() = _signUpState

    fun validateId(id: String): Boolean {
        return id.length in 6..12 && id.matches("^[a-z0-9]+$".toRegex()) // 간단한 예시 유효성 검사
    }

    fun validatePassword(password: String): Boolean {
        return password.length in 8..15 &&
                password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#\$%^&*])([A-Za-z\\d!@#\$%^&*]{8,15})$".toRegex())
    }


    fun signUp(id: String, password: String) {

        _signUpState.value = SignUpState.Loading

        if (validateId(id) && validatePassword(password)) {
            val user = User(id = id, password = password)
            userRepository.registerUser(user)
            _signUpState.value = SignUpState.Success
        } else {
            _signUpState.value = SignUpState.Failure("아이디 또는 비밀번호가 유효하지 않습니다.")
        }
    }

    sealed class SignUpState {
        object Loading : SignUpState()
        object Success : SignUpState()
        data class Failure(val errorMessage: String) : SignUpState()
    }
}