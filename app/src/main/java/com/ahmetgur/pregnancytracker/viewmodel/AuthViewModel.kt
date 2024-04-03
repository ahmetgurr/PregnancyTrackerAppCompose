package com.ahmetgur.pregnancytracker.viewmodel

import com.ahmetgur.pregnancytracker.data.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ahmetgur.pregnancytracker.Injection
import com.ahmetgur.pregnancytracker.data.UserRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance()
        )
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        _isLoggedIn.value = FirebaseAuth.getInstance().currentUser != null
    }
    fun isLoggedIn(): Boolean {
        return _isLoggedIn.value ?: false
    }

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
            checkLoginStatus()
        }
    }

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
            checkLoginStatus()
        }
    }

    fun resetPassword(email: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.resetPassword(email)
        }
    }

    fun logout() {
        viewModelScope.launch {
            userRepository.logout()
            checkLoginStatus()
        }
    }

}