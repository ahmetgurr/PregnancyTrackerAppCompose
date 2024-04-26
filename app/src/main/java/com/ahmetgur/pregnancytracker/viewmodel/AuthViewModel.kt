package com.ahmetgur.pregnancytracker.viewmodel

import com.ahmetgur.pregnancytracker.data.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ahmetgur.pregnancytracker.Injection
import com.ahmetgur.pregnancytracker.data.Note
import com.ahmetgur.pregnancytracker.data.User
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

    private val _userData = MutableLiveData<Result<User>>()
    val userData: LiveData<Result<User>> get() = _userData

    /*
    private val _updateUserData = MutableLiveData<Result<Boolean>>()
    val updateUserData: LiveData<Result<Boolean>> get() = _updateUserData
     */

    private val _updateUserData = MutableLiveData<Result<User>>()
    val updateUserData: LiveData<Result<User>> get() = _updateUserData



    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
            checkLoginStatus()
        }
    }

    fun signUp(email: String, password: String, username: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, username)
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
    fun deleteAccount() {
        viewModelScope.launch {
            userRepository.deleteAccount()
            checkLoginStatus()
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            val result = userRepository.getUserData()
            _userData.value = result
        }
    }

    fun updateUserData(user: User) {
        viewModelScope.launch {
            _updateUserData.value = userRepository.updateUserData(user)
        }
    }



}