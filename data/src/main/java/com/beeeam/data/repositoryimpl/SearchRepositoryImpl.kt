package com.beeeam.data.repositoryimpl

import com.beeeam.data.SimpleJokeApiCall
import com.beeeam.data.api.SimpleJokeApi
import com.beeeam.domain.model.Joke
import com.beeeam.domain.model.SearchResult
import com.beeeam.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: SimpleJokeApi
): SearchRepository{
    override suspend fun searchJoke(query: String): Result<SearchResult> {
        return SimpleJokeApiCall {
            api.searchJoke(query)
        }.mapCatching { response ->
        response.toModel()
        }
    }
}