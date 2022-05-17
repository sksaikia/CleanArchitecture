package com.example.githubapp.core.di.modules

import com.example.githubapp.common.network.GithubAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
//        return Retrofit.Builder()
//            .baseUrl(GithubAPI.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//    }

    @Provides
    @Singleton
    fun providesDictionaryApi() : GithubAPI {
        return Retrofit.Builder()
            .baseUrl(GithubAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubAPI::class.java)
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient{
        val client = OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideLogginInterceptor() : HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


}