package com.example.thenotebook.di

import com.example.thenotebook.api.UserAPI
import com.example.thenotebook.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// Modules to create singleton objects
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    // returns retrofit objects for userAPI
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
            .build()
    }
    // returns object of user API
    @Singleton
    @Provides
    fun providesUserAPI(retrofit: Retrofit):UserAPI{
        return retrofit.create(UserAPI::class.java)
    }
}