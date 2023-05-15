package com.thiagoperea.gitfolio.data.model

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("login") val username: String,
    @SerializedName("avatar_url") val imageUrl: String,
    @SerializedName("html_url") val pageUrl: String,
    @SerializedName("name") val fullName: String,
    val company: String,
    val location: String,
    @SerializedName("public_repos") val publicReposCount: Int,
    val followers: Int,
    val following: Int
)