package com.outdoors.breakingbadchars.detail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.outdoors.breakingbadchars.domain.Character
import com.outdoors.breakingbadchars.repository.CharactersRepository

class CharacterDetailViewModel @ViewModelInject constructor(private val charsRepository: CharactersRepository,
    @Assisted savedStateHandle: SavedStateHandle): ViewModel() {

    val selectedCharacter:Character? = savedStateHandle["character"]
}