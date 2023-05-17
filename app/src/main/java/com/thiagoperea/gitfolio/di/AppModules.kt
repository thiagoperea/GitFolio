package com.thiagoperea.gitfolio.di

import com.thiagoperea.gitfolio.data.api.GitHubApi
import com.thiagoperea.gitfolio.data.repository.GitHubRepository
import com.thiagoperea.gitfolio.ui.screens.userdetails.UserDetailsViewModel
import com.thiagoperea.gitfolio.ui.screens.userlist.UserListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
                    )
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    single { GitHubRepository(get()) }
}

val viewModelModule = module {

    viewModelOf(::UserListViewModel)

    viewModelOf(::UserDetailsViewModel)
}