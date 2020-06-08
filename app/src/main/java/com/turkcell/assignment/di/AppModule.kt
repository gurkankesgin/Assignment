package com.turkcell.assignment.di

import android.app.Application
import android.content.Context
import com.turkcell.assignment.data.db.DbManager
import com.turkcell.assignment.data.mapper.ProductMapperImpl
import com.turkcell.assignment.data.network.ApiManager
import com.turkcell.assignment.data.repositories.ProductRepository
import com.turkcell.assignment.data.network.networkutils.ResponseHandler
import com.turkcell.assignment.util.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    fun provideViewModelFactory( apiManager: ApiManager,responseHandler: ResponseHandler,dbManager: DbManager,productMapperImpl: ProductMapperImpl): ViewModelProviderFactory {
        val productRepository = ProductRepository(apiManager, dbManager, responseHandler,productMapperImpl)
        return ViewModelProviderFactory(productRepository)
    }
}