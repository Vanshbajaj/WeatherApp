package com.open.data.proto

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
private val Context.dataStore: DataStore<MyData> by dataStore("my_data_store", MyDataSerializer)
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<MyData> {
        return context.dataStore
    }
}