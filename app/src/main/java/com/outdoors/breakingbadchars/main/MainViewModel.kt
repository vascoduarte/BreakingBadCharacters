package com.outdoors.breakingbadchars.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.outdoors.breakingbadchars.database.CharactersDatabase
import com.outdoors.breakingbadchars.domain.Character
import com.outdoors.breakingbadchars.repository.CharactersRepository
import kotlinx.coroutines.*

class MainViewModel @ViewModelInject constructor(private val charsRepository:CharactersRepository,private val database: CharactersDatabase): ViewModel() {



    private val _charFilter:MutableLiveData<String> = MutableLiveData("")
    val charFilter:LiveData<String>
        get()= _charFilter


    private var _filter:MutableLiveData<ListAction> = MutableLiveData(ListAction.ALL_SEASONS)
    val filter:LiveData<ListAction>
    get()= _filter


    private val _infoSeason:MutableLiveData<String> = MutableLiveData("All Seasons")
    val infoSeason:LiveData<String>
        get()= _infoSeason



    init{

    }

    val bbCharacters:LiveData<List<Character>> = Transformations.switchMap(filter){
        when(it)
        {
            ListAction.ALL_SEASONS ->  getAllCharacters()
            ListAction.SEARCH_SEASON_1,
            ListAction.SEARCH_SEASON_2,
            ListAction.SEARCH_SEASON_3,
            ListAction.SEARCH_SEASON_4,
            ListAction.SEARCH_SEASON_5 ->  getCharactersBySeason()
           else -> getAllCharacters()
        }
    }

    fun setFilter(value:ListAction,seasonTx:String="")
    {
        _filter.value = value
        if(seasonTx!="") _infoSeason.value=seasonTx
    }
   private fun getAllCharacters():LiveData<List<Character>>
    {
        return runBlocking {
            return@runBlocking charsRepository.getNetworkCharactersList()
        }
    }
   private fun getCharactersBySeason():LiveData<List<Character>>
    {
        return runBlocking {
            return@runBlocking charsRepository.getCharactersFromSeasons(_filter.value!!.convertToSeasonSearch())
        }
    }

    fun setTextFilter(txt:String="" )
    {
        _charFilter.value=txt
    }

}