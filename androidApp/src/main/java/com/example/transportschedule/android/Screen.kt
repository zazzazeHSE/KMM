package com.example.transportschedule.android

sealed class Screen(val route: String) {
    object Main: Screen("main")
    object Schedule: Screen("schedule/{fromCode}/{toCode}") {
        fun createRoute(fromCode: String, toCode: String) =  "schedule/$fromCode/$toCode"
    }

}