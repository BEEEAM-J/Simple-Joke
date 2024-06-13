package com.beeeam.data.response

import com.beeeam.domain.model.Category

class CategoryResponse : ArrayList<String>(arrayListOf("random"))

fun CategoryResponse.toModel() = Category(category = this.toList())
