package com.example.roomdatabaseassignment.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostList(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
):Parcelable