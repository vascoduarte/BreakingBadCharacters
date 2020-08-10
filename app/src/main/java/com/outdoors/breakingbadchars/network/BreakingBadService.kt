package com.outdoors.breakingbadchars.network

import retrofit2.http.GET

interface BreakingBadService {
    @GET("/api/characters")
    suspend fun getCharsList():List<NetworkCharacter>
}