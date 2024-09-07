package com.taemallah.shoppinglist.di

import android.app.Application
import androidx.room.Room
import com.taemallah.shoppinglist.mainActivity.data.database.ShoppingDao
import com.taemallah.shoppinglist.mainActivity.data.database.ShoppingDatabase
import com.taemallah.shoppinglist.mainActivity.data.repository.ShoppingRepoImpl
import com.taemallah.shoppinglist.mainActivity.domain.repository.ShoppingRepo
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network.OpenFoodFactHttpClient
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.repository.OFFRepoImpl
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.domain.repository.OFFRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(httpClient: OpenFoodFactHttpClient): HttpClient = httpClient.gerHttpClient()

    @Provides
    @Singleton
    fun providesOFFRepository(impl: OFFRepoImpl) : OFFRepo = impl

    @Provides
    @Singleton
    fun providesShoppingDao(application: Application) : ShoppingDao = Room
        .databaseBuilder(application,ShoppingDatabase::class.java,"shopping_db")
        .build()
        .dao

    @Provides
    @Singleton
    fun providesShoppingRepo(dao: ShoppingDao) : ShoppingRepo = ShoppingRepoImpl(dao)

}