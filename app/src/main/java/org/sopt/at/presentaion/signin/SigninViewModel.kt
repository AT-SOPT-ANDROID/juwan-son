package org.sopt.at.presentaion.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.data.remote.model.User
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.presentaion.signup.SignupViewModel

class SignInViewModel : ViewModel() {
    private val userRepository = UserRepository.getInstance()

    private val _loginState = MutableLiveData<LoginState>()
    val loginState: LiveData<LoginState> get() = _loginState

    fun signIn(userId: String, password: String) {
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            if (userRepository.validateUser(userId, password)) {
                _loginState.value = LoginState.Success
            } else {
                _loginState.value = LoginState.Failure("아이디 또는 비밀번호가 잘못되었습니다.")
            }
        }
    }

    sealed class LoginState {
        object Loading : LoginState()
        object Success : LoginState()
        data class Failure(val errorMessage: String) : LoginState()
    }
}
