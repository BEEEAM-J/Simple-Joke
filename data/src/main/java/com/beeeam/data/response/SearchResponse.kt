package com.beeeam.data.response

import com.beeeam.domain.model.JokeInfo
import com.beeeam.domain.model.SearchResult

data class SearchResponse(
    val total: Int,
    val result: List<JokeResponse>
) {
    fun toModel(): SearchResult {
        val tempJokeResponse = result.map { res ->
            JokeInfo(
                categories = res.categories,
                updateDate = res.updateDate,
                imageUri = res.iconUrl,
                joke = res.value
            )
        }
        return SearchResult(
            count = total,
            result = tempJokeResponse
        )
    }
}
