package com.beeeam.data.response

import com.beeeam.domain.model.Joke
import com.google.gson.annotations.SerializedName

data class JokeResponse(
    val categories: List<Any>,
    @SerializedName("created_at") val createdDate: String,
    @SerializedName("icon_url") val iconUrl: String,
    val id: String,
    @SerializedName("updated_at") val updateDate: String,
    val url: String,
    val value: String
) {
    fun toModel() = Joke(joke = value)
}