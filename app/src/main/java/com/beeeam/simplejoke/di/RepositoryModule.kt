package com.beeeam.gocafein.di

import com.beeeam.data.repositoryimpl.CategoryRepositoryImpl
import com.beeeam.data.repositoryimpl.JokeRepositoryImpl
import com.beeeam.data.repositoryimpl.SearchRepositoryImpl
import com.beeeam.domain.repository.CategoryRepository
import com.beeeam.domain.repository.JokeRepository
import com.beeeam.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoryRepo(repoImpl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    abstract fun bindMovieDetailRepo(repoImpl: JokeRepositoryImpl): JokeRepository

    @Binds
    abstract fun bindSearchRepo(repoImpl: SearchRepositoryImpl): SearchRepository
}