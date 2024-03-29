package com.ahmetgur.pregnancytracker

sealed class Screen(val route:String){
    object LoginScreen:Screen("loginscreen")
    object RegisterScreen:Screen("registerscreen")
    object ChatRoomsScreen:Screen("chatroomscreen")
    object ChatScreen:Screen("chatscreen")
}