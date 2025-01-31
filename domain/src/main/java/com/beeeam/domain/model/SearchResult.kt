package com.beeeam.domain.model

data class SearchResult(
    val count: Int = 0,
    val result: List<JokeInfo> = listOf()
)

data class JokeInfo(
    val categories: List<String> = listOf(),
    val updateDate: String = "",
    val imageUri: String = "",
    val joke: String = "",
)
