package com.beeeam.domain.repository

import com.beeeam.domain.model.SearchResult

interface SearchRepository {
    suspend fun searchJoke(query: String): Result<SearchResult>
}