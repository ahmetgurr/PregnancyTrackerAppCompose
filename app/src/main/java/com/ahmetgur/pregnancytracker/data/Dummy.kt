package com.ahmetgur.pregnancytracker.data

import androidx.annotation.DrawableRes
import com.ahmetgur.pregnancytracker.R

data class Prf(@DrawableRes val icon: Int, val name:String)

val profiles = listOf<Prf>(
    Prf(R.drawable.baseline_settings_24, "Settings"),
    Prf(R.drawable.baseline_settings_24,"About Us"),
    Prf(R.drawable.baseline_settings_24,"Privac And Policy"),
    Prf(R.drawable.baseline_settings_24,"Terms And Conditions"),
    Prf(R.drawable.baseline_settings_24,"Contact Us"),
    Prf(R.drawable.baseline_settings_24,"Rate Us"),
    Prf(R.drawable.baseline_settings_24,"Share"),
    Prf(R.drawable.baseline_logout_24,"Logout")

)