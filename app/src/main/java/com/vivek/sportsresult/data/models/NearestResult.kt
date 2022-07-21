package com.vivek.sportsresult.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.ZonedDateTime

@Parcelize
data class NearestResult(
    val day: ZonedDateTime,
    val event: String
) : Parcelable