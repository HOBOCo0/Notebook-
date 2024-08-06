package com.example.thenotebook.di

import com.example.thenotebook.api.AuthInterceptor
import com.example.thenotebook.api.NotesAPI
import com.example.thenotebook.api.UserAPI
import com.example.thenotebook.utils.Constants
import com.example.thenotebook.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    // returns object of user API
    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder): UserAPI {
        return retrofitBuilder.build().create(UserAPI::class.java)
    }


    @Singleton
    @Provides
    fun providesNoteAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): NotesAPI {
        return retrofitBuilder.client(okHttpClient).build().create(NotesAPI::class.java)
    }
}