package com.example.quicksearch.di

import com.example.quicksearch.repository.QuickSearchApi
import com.example.quicksearch.repository.QuickSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuickSearchApi(retrofit: Retrofit): QuickSearchApi {
        return retrofit.create(QuickSearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideQuickSearchRepository(quickSearchApi: QuickSearchApi): QuickSearchRepository {
        return QuickSearchRepository(quickSearchApi)
    }
}