package com.vivek.sportsresult.network

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(
        val data: T?
    ) : ApiResponse<T>()

    data class Error(
        val exception: Throwable? = null,
        val responseCode: Int = -1,
        val errorResponse: ErrorResponse? = null
    ) : ApiResponse<Nothing>()

    fun handleResult(onSuccess: ((responseData: T?) -> Unit)?, onError: ((error: Error) -> Unit)?) {
        when (this) {
            is Success -> {
                onSuccess?.invoke(this.data)
            }
            is Error -> {
                onError?.invoke(this)
            }
        }
    }
}

data class ErrorResponse(
    val errorCode: Int = 0,
    val errorMessage: String = ""
)
