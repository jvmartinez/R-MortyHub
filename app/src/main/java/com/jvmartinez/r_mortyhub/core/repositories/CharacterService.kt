package com.jvmartinez.r_mortyhub.core.repositories

import com.jvmartinez.r_mortyhub.core.api.DataResult
import com.jvmartinez.r_mortyhub.core.data.model.Character
import com.jvmartinez.r_mortyhub.core.data.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): Response<CharacterResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): DataResult<Response<Character>>
}