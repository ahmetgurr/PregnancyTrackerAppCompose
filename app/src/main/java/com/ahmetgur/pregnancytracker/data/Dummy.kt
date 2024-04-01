package com.ahmetgur.pregnancytracker.data

import androidx.annotation.DrawableRes
import com.ahmetgur.pregnancytracker.R

data class Lib(@DrawableRes val icon: Int, val name:String)

val libraries = listOf<Lib>(
    Lib(R.drawable.baseline_settings_24, "Playlist"),
    Lib(R.drawable.baseline_settings_24,"Artists"),
    Lib(R.drawable.baseline_settings_24,"Album"),
    Lib(R.drawable.baseline_settings_24,"Songs"),
    Lib(R.drawable.baseline_settings_24,"Genre")
)