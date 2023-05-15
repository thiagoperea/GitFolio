package com.thiagoperea.gitfolio

import android.app.Application
import com.thiagoperea.gitfolio.di.dataModule
import com.thiagoperea.gitfolio.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GitFolioApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GitFolioApplication)
            modules(dataModule, viewModelModule)
        }
    }
}