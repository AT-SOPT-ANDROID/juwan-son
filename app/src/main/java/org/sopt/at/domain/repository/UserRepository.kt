package org.sopt.at.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.sopt.at.data.remote.User


class UserRepository private constructor() {
    companion object {
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository {
            return instance ?: synchronized(this) {
                instance ?: UserRepository().also { instance = it }
            }
        }
    }


    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users

    fun registerUser(user: User) {
        val currentUsers = _users.value?.toMutableList() ?: mutableListOf()
        currentUsers.add(user)
        _users.value = currentUsers
    }

    fun getUser(id: String): User? {
        return _users.value?.find { it.id == id }
    }

    fun validateUser(id: String, password: String): Boolean {
        return _users.value?.any { it.id == id && it.password == password } ?: false
    }
}