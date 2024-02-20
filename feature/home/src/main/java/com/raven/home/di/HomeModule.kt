package com.raven.home.di

import com.raven.home.data.HomeRepositoryImp
import com.raven.home.domain.HomeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindHiringSource(repository: HomeRepositoryImp): HomeDataSource
}
