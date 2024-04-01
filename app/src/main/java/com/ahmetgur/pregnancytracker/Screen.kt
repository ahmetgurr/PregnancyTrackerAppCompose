package com.ahmetgur.pregnancytracker

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String){

    sealed class LoginProceduresScreen(val lTitle: String, val lRoute: String, @DrawableRes val icon: Int
    ): Screen(lTitle,lRoute)
    {
        object Login : LoginProceduresScreen("Login", "login", R.drawable.baseline_account_circle_24)
        object Register : LoginProceduresScreen("Register", "register", R.drawable.baseline_account_circle_24)
        object Reset: LoginProceduresScreen("Reset", "reset", R.drawable.baseline_account_circle_24)
        object MainView: LoginProceduresScreen("MainView", "mainview", R.drawable.baseline_account_circle_24)
    }


    sealed class BottomScreen(val bTitle: String, val bRoute: String, @DrawableRes val icon: Int
    ): Screen(bTitle,bRoute)
    {
        object MainScreen : BottomScreen("Home", "mainscreen", R.drawable.baseline_home_filled_24)
        object Discover: BottomScreen("Discover", "discover", R.drawable.baseline_explore_discover_24)
        object Profile : BottomScreen("Profile", "profile", R.drawable.baseline_account_circle_24)
    }

    sealed class DrawerScreen(val dTitle: String, val dRoute: String,@DrawableRes val icon: Int
    ): Screen(dTitle, dRoute)
    {
        object Account : DrawerScreen("Account", "account", R.drawable.ic_account)
        object Premium : DrawerScreen("Premium", "premium", R.drawable.baseline_workspace_premium_24)
        object AddAccount : DrawerScreen("Add Account", "add_account", R.drawable.baseline_person_add_24)
    }
}

val screensInBottom = listOf(
    Screen.BottomScreen.MainScreen,
    Screen.BottomScreen.Discover,
    Screen.BottomScreen.Profile
)

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Premium,
    Screen.DrawerScreen.AddAccount
)

val screensInLoginProcedures = listOf(
    Screen.LoginProceduresScreen.Login,
    Screen.LoginProceduresScreen.Register,
    Screen.LoginProceduresScreen.Reset
)