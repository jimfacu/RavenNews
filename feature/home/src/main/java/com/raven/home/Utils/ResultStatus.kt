package com.raven.home.Utils

enum class ResultStatus {
    SUCCESS,
    FAILURE
}

data class Result<T>(
    val status: ResultStatus,
    val data: T? = null,
    val error: Throwable? = null
) {
}