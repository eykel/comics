package com.example.comics

import android.app.Application
import com.example.comics.di.apiModule
import com.example.comics.di.networkModule
import com.example.comics.di.repositoryModule
import com.example.comics.di.retrofitModule
import com.example.comics.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    retrofitModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}