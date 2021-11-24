package com.fh.unsplash.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LatestPhotoItems(
    val id: String,
    val description: String,
    val user: Users,
    val urls: Urls
) : Parcelable {
    @Parcelize
    data class Users(
        val id: String,
        val name: String
    ) : Parcelable

    @Parcelize
    data class Urls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ) : Parcelable
}


