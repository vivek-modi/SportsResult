package com.vivek.sportsresult.data.models

data class FOneResult(
    val publicationDate: String? = null,
    val seconds: String? = null,
    val tournament: String? = null,
    val winner: String? = null
) {
    val getFOneResultData: String
        get() = "$winner wins $tournament by $seconds seconds"
}