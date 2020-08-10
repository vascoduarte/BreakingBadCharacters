package com.outdoors.breakingbadchars.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val id:Int,
    val name: String,
    val birthday:String,
    val occupation:List<String>,
    val image:String,
    val status:String,
    val nickname:String,
    val appearance:List<Int>,
    val portrayed:String,
    val better_call_saul_appearance: List<Int>
):Parcelable