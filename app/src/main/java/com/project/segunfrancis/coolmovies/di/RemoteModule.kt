package com.project.segunfrancis.coolmovies.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.project.segunfrancis.coolmovies.BuildConfig
import com.project.segunfrancis.coolmovies.data.remote.api.MovieApi
import com.project.segunfrancis.coolmovies.util.AppConstants.BASE_URL
import com.project.segunfrancis.coolmovies.util.AppConstants.TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ApplicationComponent::class)
object RemoteModule {

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }

    private fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    private fun provideCache(context: Context): Cache {
        val cacheDir = context.cacheDir
        val cacheSize = 20 * 1024 * 1024    // 20 MB
        return Cache(cacheDir, cacheSize.toLong())
    }

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .callTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())
            .cache(provideCache(context))
            .build()
    }

    private fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient(context))
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(@ApplicationContext context: Context): MovieApi {
        return provideRetrofit(context).create(MovieApi::class.java)
    }

    @Provides
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }
}