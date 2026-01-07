package com.jvmartinez.r_mortyhub.core.useCase

import com.jvmartinez.r_mortyhub.core.api.DataResult
import com.jvmartinez.r_mortyhub.core.data.model.CharacterResponse
import com.jvmartinez.r_mortyhub.core.repositories.IRepository
import javax.inject.Inject

class ActionGetCharactersUseCase @Inject constructor(
    private val repository: IRepository
) {
    suspend operator fun invoke(page: Int): DataResult<CharacterResponse> {
        return repository.getCharacters(page)
    }
}