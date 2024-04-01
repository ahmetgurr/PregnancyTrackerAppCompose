package com.ahmetgur.pregnancytracker.data

import androidx.annotation.DrawableRes
import com.ahmetgur.pregnancytracker.R

data class Set(@DrawableRes val icon: Int, val name:String)

val settings = listOf<Set>(
    Set(R.drawable.baseline_settings_24, "Notification"),
    Set(R.drawable.baseline_settings_24,"Artists"),
    Set(R.drawable.baseline_settings_24,"Album"),
    Set(R.drawable.baseline_settings_24,"Songs"),
    Set(R.drawable.baseline_settings_24,"Genre")
)