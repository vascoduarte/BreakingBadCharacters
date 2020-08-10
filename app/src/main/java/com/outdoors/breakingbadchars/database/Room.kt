package com.outdoors.breakingbadchars.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import dagger.hilt.android.qualifiers.ApplicationContext

@Dao
interface CharactersDao
{
    @Query("select * from breakingbadcharacters")
    fun getAllCharacters():LiveData<List<DatabaseCharacter>>

    @Query("select * from breakingbadcharacters where appearance like :season")
    fun getSeasonCharacters(season:String):LiveData<List<DatabaseCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg character: DatabaseCharacter)
}

@Database(entities = [DatabaseCharacter::class], version = 1, exportSchema = false)
abstract class CharactersDatabase:RoomDatabase(){
    abstract val charactersDao:CharactersDao
}