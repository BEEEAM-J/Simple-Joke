package com.beeeam.domain.repository

import com.beeeam.domain.model.Joke

interface JokeRepository {
    suspend fun loadJoke(category: String): Result<Joke>
}