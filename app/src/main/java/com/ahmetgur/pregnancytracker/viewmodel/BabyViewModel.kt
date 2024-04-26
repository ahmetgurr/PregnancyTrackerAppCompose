package com.ahmetgur.pregnancytracker.viewmodel

import com.ahmetgur.pregnancytracker.data.Result
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ahmetgur.pregnancytracker.Injection
import com.ahmetgur.pregnancytracker.data.Baby
import com.ahmetgur.pregnancytracker.data.BabyRepository
import kotlinx.coroutines.launch

class BabyViewModel : ViewModel() {
    private val babyRepository: BabyRepository

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> get() = _isLoggedIn

    init {
        babyRepository = BabyRepository(
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

    private val _babyData = MutableLiveData<Result<Baby>>()
    val babyData: LiveData<Result<Baby>> get() = _babyData

    fun updateBabyData(baby: Baby) {
        viewModelScope.launch {
            babyRepository.updateBabyData(baby)
        }
    }

    fun getBabyData() {
        viewModelScope.launch {
            val result = babyRepository.getBabyData()
            _babyData.value = result
        }
    }

}