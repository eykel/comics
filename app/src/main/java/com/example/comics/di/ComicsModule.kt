package com.example.comics.di

import android.app.Application
import com.example.comics.data.networking.ComicNetworking
import com.example.comics.data.networking.impl.ComicNetworkingImpl
import com.example.comics.data.service.ComicApi
import com.example.comics.data.util.Authenticator
import com.example.comics.domain.repository.ComicRepository
import com.example.comics.domain.repository.impl.ComicRepositoryImpl
import com.example.comics.ui.home.HomeViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { Authenticator() }

    single<ComicNetworking> {
        ComicNetworkingImpl(
            api = get(),
            authenticator = get()
        )
    }
}

val viewModelModule = module {

    viewModel {
        HomeViewModel(
            comicRepository = get()
        )
    }
}

val repositoryModule = module {

    single<ComicRepository> {
        ComicRepositoryImpl(
            comicNetworking = get()
        )
    }
}

val apiModule = module {

    fun provideComicApi(retrofit: Retrofit): ComicApi {
        return retrofit.create(ComicApi::class.java)
    }

    single { provideComicApi(get()) }
}

val retrofitModule = module {

    fun provideRetrofit(client: OkHttpClient, gson : Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    fun provideHttpClient(cache: Cache): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .cache(cache)

        return okHttpClientBuilder.build()
    }

    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    fun provideGson(): Gson = GsonBuilder().create()

    single { provideCache(androidApplication()) }
    single { provideHttpClient(get()) }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}