package com.example.test11.di

import com.example.test11.data.common.HandleResponse
import com.example.test11.data.remote.service.AccountsService
import com.example.test11.data.repository.AccountsRepositoryImpl
import com.example.test11.domain.repository.AccountsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAccountsRepository(
        accountsService:AccountsService,
        handler: HandleResponse
    ): AccountsRepository {
        return AccountsRepositoryImpl(
            accountsService = accountsService,
            handler = handler
        )
    }
}