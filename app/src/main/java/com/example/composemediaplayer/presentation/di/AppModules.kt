package com.example.composemediaplayer.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.composemediaplayer.data.SongsRepositoryImpl
import com.example.composemediaplayer.data.database.SongsDao
import com.example.composemediaplayer.data.database.SongsDatabase
import com.example.composemediaplayer.domain.SongsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val NAME = "songs_database"

    @Provides
    @Singleton
    fun provideDatabase(application: Application): SongsDatabase {
        return Room.databaseBuilder(
            application,
            SongsDatabase::class.java, NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    //Provides singleton SongsDao to the database
    @Provides
    @Singleton
    fun provideSongsDao(database: SongsDatabase): SongsDao {
        return database.songsDao
    }


}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(dao: SongsDao): SongsRepository {
        return SongsRepositoryImpl(dao)
    }
}

