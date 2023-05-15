package com.thiagoperea.gitfolio.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")val name: String,
    @SerializedName("avatar_url")val imageUrl: String,
    @SerializedName("html_url")val pageUrl: String
)