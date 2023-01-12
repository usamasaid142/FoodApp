package com.codewithosm.foodapp.mdel



data class ResturantResponseItem(
    val address: String,
    val deliveryCharge: Int,
    val hours: Hours,
    val image: String,
    val menus: List<Menu>,
    val name: String
)