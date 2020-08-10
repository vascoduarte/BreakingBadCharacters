package com.outdoors.breakingbadchars.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.outdoors.breakingbadchars.R
import com.outdoors.breakingbadchars.databinding.MainFragmentBinding
import com.outdoors.breakingbadchars.util.CharacterClickListener
import com.outdoors.breakingbadchars.util.CharacterListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<MainFragmentBinding>(inflater,R.layout.main_fragment, container, false)

        binding.mainViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val charsListManager = LinearLayoutManager(activity)
        binding.charsListView.layoutManager = charsListManager

        val charsListAdapter = CharacterListAdapter(CharacterClickListener { character ->
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToCharacterDetail(character))
        })
        binding.charsListView.adapter = charsListAdapter

        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       inflater.inflate(R.menu.menu_list, menu)

        val searchItem = menu.findItem(R.id.searchBt)

        val searchView:SearchView = searchItem.actionView as SearchView
        searchView.isIconified = false
        searchView.queryHint = "Character name..."

        searchItem.setOnMenuItemClickListener(object:MenuItem.OnMenuItemClickListener{
            override fun onMenuItemClick(item: MenuItem?): Boolean {
                Log.i("TEST", "Item CLICK")
                searchView.isIconified = false
                return false
            }

        })
        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            Log.i("TEST - FOCUS", hasFocus.toString())
            searchView.setQuery("", false)
            if(!hasFocus)
            {
                viewModel.setTextFilter("")
                searchView.clearFocus()

            }
        }


        searchView.setOnCloseListener {
            Log.i("TEST", "CLOSE")

            viewModel.setTextFilter("")
            searchView.clearFocus()
            searchItem.collapseActionView()

            false
        }
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.setTextFilter(newText.toLowerCase())}

                return false
            }

        })



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {

            R.id.showAll -> viewModel.setFilter(ListAction.ALL_SEASONS, getString(R.string.filter_all_seasons))
            R.id.season1 -> viewModel.setFilter(ListAction.SEARCH_SEASON_1,getString(R.string.filter_season1))
            R.id.season2 -> viewModel.setFilter(ListAction.SEARCH_SEASON_2,getString(R.string.filter_season2))
            R.id.season3 -> viewModel.setFilter(ListAction.SEARCH_SEASON_3,getString(R.string.filter_season3))
            R.id.season4 -> viewModel.setFilter(ListAction.SEARCH_SEASON_4,getString(R.string.filter_season4))
            R.id.season5 -> viewModel.setFilter(ListAction.SEARCH_SEASON_5,getString(R.string.filter_season5))
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

}