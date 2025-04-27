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

    private val _signupState = MutableLiveData<SignupState>()
    val signupState: LiveData<SignupState> get() = _signupState

    fun signUp(id: String, password: String) {
        val user = User(id = id, password = password)
        userRepository.registerUser(user)
        _signupState.value = SignupState.Success
    }

    sealed class SignupState {
        object Success : SignupState()
        data class Failure(val errorMessage: String) : SignupState()
    }
}