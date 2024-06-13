package com.beeeam.domain.usecase

import com.beeeam.domain.model.Joke
import com.beeeam.domain.repository.JokeRepository

class GetJokeUseCase(private val repo: JokeRepository) {
    suspend operator fun invoke(category: String): Result<Joke> {
        return repo.loadJoke(category)
    }
}