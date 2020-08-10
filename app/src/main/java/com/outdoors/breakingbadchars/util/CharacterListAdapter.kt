package com.outdoors.breakingbadchars.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.outdoors.breakingbadchars.databinding.CharacterItemListBinding
import com.outdoors.breakingbadchars.domain.Character

class CharacterListAdapter(val onClickListener:CharacterClickListener):ListAdapter<Character,CharacterListAdapter.ViewHolder>(CharacterListDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(onClickListener,item)
    }

    class ViewHolder private constructor(val binding:CharacterItemListBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(characterClickListener: CharacterClickListener, item:Character)
        {
            binding.characterItem = item
            binding.itemClickListener = characterClickListener
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterItemListBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class CharacterListDiffCallBack(): DiffUtil.ItemCallback<Character>()
{
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }


}

class CharacterClickListener(val clickListener: (character: Character) -> Unit) {
    fun onClick(character:Character) = clickListener(character)
}