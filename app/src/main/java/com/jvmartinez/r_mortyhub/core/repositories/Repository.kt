package com.jvmartinez.r_mortyhub.core.repositories

import com.jvmartinez.r_mortyhub.core.api.DataResult
import com.jvmartinez.r_mortyhub.core.api.apiResponse
import com.jvmartinez.r_mortyhub.core.data.model.CharacterResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
    private val characterService: CharacterService
): IRepository  {
    override suspend fun getCharacters(page: Int): DataResult<CharacterResponse> {
        return  apiResponse {
            characterService.getCharacters(page)
        }

    }
}