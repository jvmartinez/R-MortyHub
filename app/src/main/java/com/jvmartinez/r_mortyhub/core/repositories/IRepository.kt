package com.jvmartinez.r_mortyhub.core.repositories

import com.jvmartinez.r_mortyhub.core.api.DataResult
import com.jvmartinez.r_mortyhub.core.data.model.CharacterResponse

interface IRepository {
    suspend fun getCharacters(page: Int): DataResult<CharacterResponse>
}