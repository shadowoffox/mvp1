package com.example.mvp1.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository (
    val id: String,
    val name: String,
    val forksCount: Int
):Parcelable