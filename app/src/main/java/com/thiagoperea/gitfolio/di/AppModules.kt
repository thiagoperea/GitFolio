package com.thiagoperea.gitfolio.di

import com.thiagoperea.gitfolio.data.api.GitHubApi
import com.thiagoperea.gitfolio.data.repository.GitHubRepository
import com.thiagoperea.gitfolio.ui.screens.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit


val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()
            .create(GitHubApi::class.java)
    }

    singleOf(::GitHubRepository)
}

val viewModelModule = module {

    viewModelOf(::UserListViewModel)
}