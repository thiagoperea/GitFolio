package com.thiagoperea.gitfolio.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    val name: String,
    @SerializedName("html_url") val url: String,
    val description: String?,
    @SerializedName("stargazers_count") val starCount: Int,
    @SerializedName("watchers_count") val watcherCount: Int,
    @SerializedName("forks_count") val forkCount: Int
)
