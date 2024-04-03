package com.ahmetgur.pregnancytracker.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ahmetgur.pregnancytracker.Screen

class MainViewModel: ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.BottomScreen.MainScreen)

    // currentScreen is a mutableState that holds the current /mevcut ekranı tutan bir mutableState'dir, hangi sayfada olduğumuzu tutar
    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen){
        _currentScreen.value = screen
    }

}