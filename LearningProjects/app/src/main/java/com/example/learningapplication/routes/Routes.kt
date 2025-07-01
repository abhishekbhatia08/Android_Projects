package com.example.learningapplication.routes

object Routes {
    const val LOGIN = "login"
    const val HOME = "home/{userName}"

    fun homeRoute(userName: String) = "home/$userName"
}
