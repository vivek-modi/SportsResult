package com.vivek.sportsresult.ui.screen.navigation

sealed class ScreenRoute(val route:String){
    object Home : ScreenRoute("Home")
    object Result : ScreenRoute("Result")
}
