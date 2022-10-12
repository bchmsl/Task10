package com.bchmsl.task10.di

import com.bchmsl.task10.data.repository.MessagesRepositoryImpl
import com.bchmsl.task10.domain.repository.MessagesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMainRepository(messagesRepositoryImpl: MessagesRepositoryImpl): MessagesRepository
}