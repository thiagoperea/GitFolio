package com.thiagoperea.gitfolio.data.api

import com.thiagoperea.gitfolio.data.model.RepositoryResponse
import com.thiagoperea.gitfolio.data.model.UserDetailsResponse
import com.thiagoperea.gitfolio.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubApi {

    @GET("users")
    suspend fun listUsers(): List<UserResponse>

    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): UserDetailsResponse

    @GET("users/{username}/repos")
    suspend fun listRepos(@Path("username") username: String): List<RepositoryResponse>
}