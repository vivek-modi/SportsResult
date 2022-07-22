package com.vivek.sportsresult.navigation

sealed class ScreenRoute(val route:String){
    object Splash : ScreenRoute("Splash")
    object Home : ScreenRoute("Home")
    object Result : ScreenRoute("Result")
}
