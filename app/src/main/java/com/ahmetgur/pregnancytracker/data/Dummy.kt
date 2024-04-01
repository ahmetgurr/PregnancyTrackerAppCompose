package com.ahmetgur.pregnancytracker.data

import androidx.annotation.DrawableRes
import com.ahmetgur.pregnancytracker.R

data class Prf(@DrawableRes val icon: Int, val name:String)

val profiles = listOf<Prf>(
    Prf(R.drawable.baseline_settings_24, "Notification"),
    Prf(R.drawable.baseline_settings_24,"Artists"),
    Prf(R.drawable.baseline_settings_24,"Album"),
    Prf(R.drawable.baseline_settings_24,"Songs"),
    Prf(R.drawable.baseline_settings_24,"Genre")
)