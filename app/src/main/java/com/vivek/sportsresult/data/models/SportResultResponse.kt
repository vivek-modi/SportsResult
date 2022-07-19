package com.vivek.sportsresult.data.models

data class SportResultResponse(
    val f1Results: List<FOneResult>? = null,
    val nbaResults: List<NbaResult>? = null,
    val Tennis: List<Tennis>? = null
)
