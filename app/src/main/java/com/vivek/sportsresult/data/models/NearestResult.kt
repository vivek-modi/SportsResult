package com.vivek.sportsresult.data.models

import android.os.Parcelable
import com.vivek.sportsresult.util.KZonedDateTimeSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
@Parcelize
data class NearestResult(
    @Serializable(KZonedDateTimeSerializer::class)
    val day: ZonedDateTime,
    val event: String
) : Parcelable