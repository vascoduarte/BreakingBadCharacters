package com.outdoors.breakingbadchars.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.outdoors.breakingbadchars.R
import com.outdoors.breakingbadchars.databinding.CharacterDetailFragmentBinding

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.character_detail_fragment.view.*
import kotlinx.android.synthetic.main.season.view.*

@AndroidEntryPoint
class CharacterDetail : Fragment() {

    private val detailViewModel: CharacterDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<CharacterDetailFragmentBinding>(inflater,R.layout.character_detail_fragment, container, false)

        val arguments = CharacterDetailArgs.fromBundle(requireArguments())

        requireActivity().activityToolbar.title=arguments.character.name

        binding.detailViewModel = detailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        for(i in arguments.character.appearance)
        {
            var season=  inflater.inflate(R.layout.season,null)
            season.seasonTx.text = i.toString()
            binding.seasons.addView(season)
        }

        for(i in arguments.character.better_call_saul_appearance)
        {
            var season=  inflater.inflate(R.layout.season,null)
            season.seasonTx.text = i.toString()
            binding.BCSseasons.addView(season)
        }

        return binding.root
    }
    private fun addSeasons( inflater: LayoutInflater,seasons:List<Int>)
    {

    }
}