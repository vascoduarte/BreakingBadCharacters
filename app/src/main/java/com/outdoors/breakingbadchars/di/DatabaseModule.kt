package com.outdoors.breakingbadchars.di

import android.content.Context
import androidx.room.Room
import com.outdoors.breakingbadchars.database.CharactersDao
import com.outdoors.breakingbadchars.database.CharactersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule{

    lateinit var INSTANCE:CharactersDatabase

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): CharactersDatabase {
        // Make sure a read is made before writing so our onCreate callback is executed first
        INSTANCE =  Room.databaseBuilder(
            context,
            CharactersDatabase::class.java, "BBCharacters.db"
        ).build()
        return INSTANCE
    }

    @Provides
    fun provideObjectDao(database: CharactersDatabase): CharactersDao {
        return database.charactersDao
    }
}

