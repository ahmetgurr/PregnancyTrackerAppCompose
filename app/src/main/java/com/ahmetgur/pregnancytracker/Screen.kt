package com.ahmetgur.pregnancytracker

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String){

/*
    object LoginScreen:Screen("loginscreen")
    object RegisterScreen:Screen("registerscreen")
    object ResetScreen:Screen("resetscreen")
    object MainScreen:Screen("mainscreen")
    object SecondScreen:Screen("secondscreen")
    object ThirdScreen:Screen("thirdscreen")

 */

    sealed class LoginProceduresScreen(val lTitle: String, val lRoute: String, @DrawableRes val icon: Int
    ): Screen(lTitle,lRoute)
    {
        object Login : LoginProceduresScreen("Login", "login", R.drawable.baseline_apps_24)
        object Register : LoginProceduresScreen("Register", "register", R.drawable.baseline_apps_24)
        object Reset: LoginProceduresScreen("Reset", "reset", R.drawable.baseline_apps_24)
    }


    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int
    ): Screen(bTitle,bRoute)
    {
        object MainScreen : BottomScreen("MainScreen", "mainscreen", R.drawable.baseline_apps_24)
        object Library : BottomScreen("Library", "library", R.drawable.baseline_apps_24)
        object Browse: BottomScreen("Browse", "browse", R.drawable.baseline_apps_24)
    }

    sealed class DrawerScreen(val dTitle: String, val dRoute: String,@DrawableRes val icon: Int
    ): Screen(dTitle, dRoute)
    {
        object Account : DrawerScreen("Account", "account", R.drawable.ic_account)
        object Subscription : DrawerScreen("Subscription", "subscribe", R.drawable.baseline_apps_24)
        object AddAccount : DrawerScreen("Add Account", "add_account", R.drawable.baseline_apps_24)
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.MainScreen,
    Screen.BottomScreen.Browse,
    Screen.BottomScreen.Library
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)
