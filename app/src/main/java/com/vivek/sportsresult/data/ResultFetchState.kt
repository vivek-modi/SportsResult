package com.vivek.sportsresult.data

import com.vivek.sportsresult.data.models.SportResultResponse

sealed class ResultFetchState {
    data class OnSuccess(val sportResultResponse: SportResultResponse) : ResultFetchState()
    object OnEmpty : ResultFetchState()
    data class OnError(val response: String?) : ResultFetchState()
    data class IsLoading(val isLoading: Boolean = true) : ResultFetchState()
}