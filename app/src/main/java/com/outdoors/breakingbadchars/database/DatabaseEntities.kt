package com.outdoors.breakingbadchars.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.outdoors.breakingbadchars.domain.Character

@Entity(tableName = "breakingbadcharacters")
class DatabaseCharacter constructor(
    @PrimaryKey val id:Int,
    val name: String,
    val birthday:String,
    val image:String,
    val status:String,
    val nickname:String,
    val portrayed:String,
    val better_call_saul_appearance:String, //list
    val occupation:String, //list
    val appearance:String //list
)

fun List<DatabaseCharacter>.asDomainModel(): List<Character> {
    return map {

       val bbs_seasons = if(it.better_call_saul_appearance=="")  listOf<Int>() else {it.better_call_saul_appearance.split(";").map {it.toInt()}}
       val bb_seasons = if(it.appearance=="")  listOf<Int>() else {it.appearance.split(";").map {it.toInt()}}

        Character (
            id = it.id,
            name = it.name,
            birthday = it.birthday,
            image = it.image,
            status = it.status,
            nickname = it.nickname,
            portrayed = it. portrayed,


            better_call_saul_appearance = bbs_seasons,
            occupation = it.occupation.split(";"),
            appearance = bb_seasons
        )
    }
}