package com.outdoors.breakingbadchars.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.outdoors.breakingbadchars.database.CharactersDatabase
import com.outdoors.breakingbadchars.database.asDomainModel
import com.outdoors.breakingbadchars.domain.Character
import com.outdoors.breakingbadchars.network.BreakingBadService
import com.outdoors.breakingbadchars.network.NetworkStatus
import com.outdoors.breakingbadchars.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersRepository @Inject constructor(private val network:BreakingBadService, private val database:CharactersDatabase ) {

    suspend fun getNetworkCharactersList():LiveData<List<Character>>
    {
        withContext(Dispatchers.IO)
        {
            try{
                val chars =  network.getCharsList()
                database.charactersDao.insertAll(*chars.asDataBaseModel())
            }
             catch (e:Exception)
             {

             }

        }
        return Transformations.map(database.charactersDao.getAllCharacters()) {
            it.asDomainModel()
        }
    }

    suspend fun getCharactersFromSeasons(season:String):LiveData<List<Character>>
    {
      return withContext(Dispatchers.IO)
        {

            return@withContext Transformations.map(database.charactersDao.getSeasonCharacters(season)){
                it.asDomainModel()
            }

        }
    }
}