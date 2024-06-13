package com.beeeam.data.repositoryimpl

import com.beeeam.data.SimpleJokeApiCall
import com.beeeam.data.api.SimpleJokeApi
import com.beeeam.domain.model.Joke
import com.beeeam.domain.repository.JokeRepository
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    val api: SimpleJokeApi
): JokeRepository {
    override suspend fun loadJoke(category: String): Result<Joke> {
        return SimpleJokeApiCall {
            api.getJoke(category)
        }.mapCatching { response ->
            response.toModel()
        }
    }
}