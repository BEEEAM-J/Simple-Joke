package com.beeeam.gocafein.di

import com.beeeam.domain.repository.CategoryRepository
import com.beeeam.domain.repository.JokeRepository
import com.beeeam.domain.usecase.GetCategoryUseCase
import com.beeeam.domain.usecase.GetJokeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetCategory(repo: CategoryRepository): GetCategoryUseCase {
        return GetCategoryUseCase(repo)
    }

    @Singleton
    @Provides
    fun provideGetJoke(repo: JokeRepository): GetJokeUseCase {
        return GetJokeUseCase(repo)
    }
}