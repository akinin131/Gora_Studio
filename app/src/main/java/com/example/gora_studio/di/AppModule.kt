package com.example.gora_studio.di

import com.example.gora_studio.data.network.NewsApiService
import com.example.gora_studio.data.network.RetrofitInstance
import com.example.gora_studio.data.repository.NewsByQueryAndDateRepositoryImpl
import com.example.gora_studio.domain.repository.NewsByQueryAndDateRepository
import com.example.gora_studio.domain.usecase.GetNewsByQueryAndDateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService {
        return RetrofitInstance.newsApiService
    }

    @Provides
    @Singleton
    fun provideNewsByQueryAndDateRepository(newsApiService: NewsApiService): NewsByQueryAndDateRepository {
        return NewsByQueryAndDateRepositoryImpl(newsApiService)
    }

    @Provides
    @Singleton
    fun provideGetNewsByQueryAndDateUseCase(repository: NewsByQueryAndDateRepository): GetNewsByQueryAndDateUseCase {
        return GetNewsByQueryAndDateUseCase(repository)
    }

}
