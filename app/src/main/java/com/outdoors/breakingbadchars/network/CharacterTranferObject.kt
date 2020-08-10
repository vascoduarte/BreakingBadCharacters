package com.outdoors.breakingbadchars.network

import androidx.room.PrimaryKey
import com.outdoors.breakingbadchars.database.DatabaseCharacter
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCharacter(
    val char_id:Int,
    val name: String,
    val birthday:String,
    val occupation:List<String>,
    val img:String,
    val status:String,
    val nickname:String,
    val appearance:List<Int>,
    val portrayed:String,
    val better_call_saul_appearance:List<Int>
)

fun List<NetworkCharacter>.asDataBaseModel():Array<DatabaseCharacter> {
    return map{

        DatabaseCharacter(
            id = it.char_id,
            name = it.name,
            birthday =  it.birthday,
            image = it.img,
            status = it.status,
            nickname = it.nickname,
            portrayed = it.portrayed,
           appearance = it.appearance.joinToString(";"),
            occupation = it.occupation.joinToString(";"),
            better_call_saul_appearance = it.better_call_saul_appearance.joinToString(";")
        )
    }.toTypedArray()
}