package com.beeeam.domain.repository

import com.beeeam.domain.model.Category

interface CategoryRepository {
    suspend fun loadCategory(): Result<Category>
}