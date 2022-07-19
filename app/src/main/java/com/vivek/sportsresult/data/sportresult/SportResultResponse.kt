package com.vivek.sportsresult.data.sportresult

data class SportResultResponse(
    val f1Results: List<FOneResult>? = null,
    val nbaResults: List<NbaResult>? = null,
    val Tennis: List<Tennis>? = null
)
