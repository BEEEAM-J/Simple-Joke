package com.beeeam.data.repositoryimpl

import com.beeeam.data.SimpleJokeApiCall
import com.beeeam.data.api.SimpleJokeApi
import com.beeeam.data.response.toModel
import com.beeeam.domain.model.Category
import com.beeeam.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: SimpleJokeApi
): CategoryRepository {
    override suspend fun loadCategory(): Result<Category> {
        return SimpleJokeApiCall {
            api.getCategoryList()
        }.mapCatching { response ->
            response.toModel()
        }
    }

}