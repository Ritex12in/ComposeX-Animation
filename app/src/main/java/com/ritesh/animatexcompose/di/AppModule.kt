package com.ritesh.animatexcompose.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.ritesh.animatexcompose.data.repository.AnimationRepositoryImpl
import com.ritesh.animatexcompose.data.source.AnimationDataSource
import com.ritesh.animatexcompose.domain.repository.AnimationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "app_preferences")

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataSource():AnimationDataSource{
        return AnimationDataSource()
    }

    @Provides
    @Singleton
    fun provideAnimationRepository(
        dataSource: AnimationDataSource
    ): AnimationRepository{
        return AnimationRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = context.dataStore
}