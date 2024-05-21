package com.kirara.core.di

import com.kirara.core.data.datasource.local.db.AppDatabase
import com.kirara.core.data.datasource.remote.ApiService
import com.kirara.core.data.repository.product.DbProductRepositoryImpl
import com.kirara.core.data.repository.product.ProductRepositoryImpl
import com.kirara.core.domain.repository.product.DbProductRepository
import com.kirara.core.domain.repository.product.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService) : ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDbProductRepository(db: AppDatabase): DbProductRepository {
        return DbProductRepositoryImpl(db)
    }

}