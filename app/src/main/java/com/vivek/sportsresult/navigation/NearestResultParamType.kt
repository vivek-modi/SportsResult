package com.vivek.sportsresult.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.vivek.sportsresult.data.models.NearestResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NearestResultParamType : NavType<ArrayList<NearestResult>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ArrayList<NearestResult>? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ArrayList<NearestResult> {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: ArrayList<NearestResult>) {
        bundle.putParcelableArrayList(key, value)
    }
}