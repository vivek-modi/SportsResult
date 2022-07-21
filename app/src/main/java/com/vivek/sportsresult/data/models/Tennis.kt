package com.vivek.sportsresult.data.models

data class Tennis(
    val looser: String? = null,
    val numberOfSets: Int? = null,
    val publicationDate: String? = null,
    val tournament: String? = null,
    val winner: String? = null
) {
    val getTennisData: String
        get() = "$tournament: $winner wins against $looser in $numberOfSets sets"
}
