package com.beeeam.gocafein.di

import com.beeeam.data.api.SimpleJokeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideSimpleJokeApi(retrofit: Retrofit): SimpleJokeApi {
        return retrofit.create(SimpleJokeApi::class.java)
    }
}
