package com.onurbarman.inviousgchallenge.di

import com.onurbarman.inviousgchallenge.data.repository.InvioUsgRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideInvioUsgRepository(): InvioUsgRepository {
        return InvioUsgRepository()
    }
}