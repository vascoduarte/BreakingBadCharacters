package com.outdoors.breakingbadchars.main

import android.content.Context
import android.content.res.Resources
import com.outdoors.breakingbadchars.R

enum class ListAction{
    SEARCH_NAME,
    ALL_SEASONS,
    SEARCH_SEASON_1,
    SEARCH_SEASON_2,
    SEARCH_SEASON_3,
    SEARCH_SEASON_4,
    SEARCH_SEASON_5
}

fun ListAction.convertToSeasonSearch():String
{
  return when(this)
   {
       ListAction.SEARCH_SEASON_1 -> "%1%"
       ListAction.SEARCH_SEASON_2 -> "%2%"
       ListAction.SEARCH_SEASON_3 -> "%3%"
       ListAction.SEARCH_SEASON_4 -> "%4%"
       ListAction.SEARCH_SEASON_5 -> "%5%"
      else -> ""
   }
}
fun ListAction.seasonNumber(ctx:Context):String
{
    return when(this)
    {
        ListAction.SEARCH_SEASON_1 -> ctx.getString(R.string.filter_season1)
        ListAction.SEARCH_SEASON_2 -> "2"
        ListAction.SEARCH_SEASON_3 -> "3"
        ListAction.SEARCH_SEASON_4 -> "4"
        ListAction.SEARCH_SEASON_5 -> "5"
        else -> "All Seasons"
    }
}