package com.beeeam.domain.usecase

import com.beeeam.domain.model.Joke
import com.beeeam.domain.model.SearchResult
import com.beeeam.domain.repository.SearchRepository

class SearchUseCase(private val repo: SearchRepository) {
   suspend operator fun invoke(query: String): Result<SearchResult> {
       return repo.searchJoke(query)
   }
}