package com.vivek.sportsresult.data.models

data class NbaResult(
    val gameNumber: Int? = null,
    val looser: String? = null,
    val mvp: String? = null,
    val publicationDate: String? = null,
    val tournament: String? = null,
    val winner: String? = null
) {
    val getNbaResultData: String
        get() = "$mvp leads $winner to game $gameNumber win in the $tournament"
}