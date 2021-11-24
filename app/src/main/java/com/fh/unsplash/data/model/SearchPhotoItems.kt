package com.fh.unsplash.data.model

data class SearchPhotoItems(
    val id: String,
    val description: String,
    val user: UnSplashUser,
    val urls: UnSplashPhotoUrls
) {

    data class UnSplashUser(
        val name: String,
        val username: String
    ) {
        val attributionUrl get() = "https://unsplash.com/$username?utm_sources=ImageApp&Utm_medium=referral"
    }

    data class UnSplashPhotoUrls(
        val raw: String,
        val full: String,
        val small: String,
        val regular: String,
        val thumb: String
    )
}
