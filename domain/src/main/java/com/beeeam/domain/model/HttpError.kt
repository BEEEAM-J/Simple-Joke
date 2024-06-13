package com.beeeam.domain.model

class HttpError(val code: Int, val errorBody: String) : Exception()