package com.outdoors.breakingbadchars.util

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.outdoors.breakingbadchars.R
import com.outdoors.breakingbadchars.domain.Character

@BindingAdapter("listData","filterText","emptyView")
fun bindListView(recyclerView: RecyclerView, data:List<Character>?,filterText:String, emptyView:View)
{
    var newList:List<Character>?=null

    val adapter=recyclerView.adapter as CharacterListAdapter
    if(filterText!="")
    {
        data?.let {
            newList = data.filter { it -> it.name.toLowerCase().contains(filterText) }
            adapter.submitList( newList)

        }
    }
    else {
        newList=data
        adapter.submitList(data)

    }

    if(newList==null)
    {
        emptyView.visibility =  View.VISIBLE
    }
    else
    {
        emptyView.visibility = if(newList!!.isEmpty())  View.VISIBLE else View.GONE
    }

}

@BindingAdapter("imgSrc")
fun bindCharacterImage(imgView: ImageView, imgUrl:String)
{
        val imgUri=imgUrl.toUri()
        Glide.with(imgView.context)
            .load(imgUri)
            .placeholder(R.drawable.ic_bb_logo)
            .circleCrop()
            .into(imgView)
}
@BindingAdapter("unknownBirthday")
fun bindCharacterBirthday(txtView:TextView,txt:String)
{
    txtView.visibility = if(txt.toLowerCase() == "unknown")  View.INVISIBLE else View.VISIBLE
}
@BindingAdapter("characterStatus")
fun bindCharacterStatus(txtView:TextView,txt:String)
{

    when(txt.toLowerCase())
    {
        "deceased" -> txtView.setTextColor( ResourcesCompat.getColor(txtView.resources, R.color.deceased_text, null))
        "presumed dead" -> txtView.setTextColor( ResourcesCompat.getColor(txtView.resources, R.color.presumed_dead_text, null))
        else -> txtView.setTextColor( ResourcesCompat.getColor(txtView.resources, R.color.alive_text, null))
    }
}
@BindingAdapter("haveNoSeasons")
fun bindSeasons(view:View,list:List<Int>)
{
    view.visibility = if(list.isEmpty())  View.INVISIBLE else View.VISIBLE
}
