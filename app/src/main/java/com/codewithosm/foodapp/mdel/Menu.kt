package com.codewithosm.foodapp.mdel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Menu(
    val name: String,
    val price: Double,
    val url: String,
    var totalCount:Int
):Parcelable