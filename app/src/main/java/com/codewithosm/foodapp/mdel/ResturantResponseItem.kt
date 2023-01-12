package com.codewithosm.foodapp.mdel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResturantResponseItem(
    val address: String,
    val deliveryCharge: Int,
    val hours: Hours,
    val image: String,
    val menus: List<Menu>,
    val name: String
):Parcelable