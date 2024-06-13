package com.beeeam.domain.usecase

import com.beeeam.domain.model.Category
import com.beeeam.domain.repository.CategoryRepository

class GetCategoryUseCase(private val repo: CategoryRepository) {
    suspend operator fun invoke(): Result<Category> {
        return repo.loadCategory()
    }
}