package com.codewithosm.foodapp.mdel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Hours(
    val Friday: String,
    val Monday: String,
    val Saturday: String,
    val Sunday: String,
    val Thursday: String,
    val Tuesday: String,
    val Wednesday: String
):Parcelable
