package com.vivek.sportsresult.data

import com.vivek.sportsresult.data.models.NearestResult

sealed class ResultFetchState {
    data class OnSuccess(val nearestResult: ArrayList<NearestResult>) : ResultFetchState()
    object OnEmpty : ResultFetchState()
    data class OnError(val response: String?) : ResultFetchState()
    object IsLoading : ResultFetchState()
}