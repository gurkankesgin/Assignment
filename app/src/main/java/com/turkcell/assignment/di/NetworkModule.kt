package com.turkcell.assignment.di

import android.app.Application
import com.turkcell.assignment.data.network.ApiManager
import com.turkcell.assignment.data.network.networkutils.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory) = Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/")
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideNetworkApi(retrofit: Retrofit) = retrofit.create(ApiManager::class.java)

    @Provides
    @Reusable
    fun provideResponseHandler() = ResponseHandler()

    @Provides
    @Reusable
    fun provideGsonConverterFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Reusable
    fun provideCache(app: Application) = Cache(File(app.cacheDir, "REST_CACHE"), 50 * 1024 * 1024)

    @Provides
    @Reusable
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
}