package com.ahmetgur.pregnancytracker.data

import androidx.annotation.DrawableRes
import com.ahmetgur.pregnancytracker.R

data class Lib(@DrawableRes val icon: Int, val name:String)

val libraries = listOf<Lib>(
    Lib(R.drawable.ic_launcher_foreground, "Playlist"),
    Lib(R.drawable.ic_launcher_foreground,"Artists"),
    Lib(R.drawable.ic_launcher_foreground,"Album"),
    Lib(R.drawable.ic_launcher_foreground,"Songs"),
    Lib(R.drawable.ic_launcher_foreground,"Genre")
)