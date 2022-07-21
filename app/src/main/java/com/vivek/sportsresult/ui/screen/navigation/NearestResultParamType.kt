package com.vivek.sportsresult.ui.screen.navigation

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavType
import com.google.gson.Gson
import com.vivek.sportsresult.data.models.NearestResult

class NearestResultParamType : NavType<ArrayList<NearestResult>>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): ArrayList<NearestResult>? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): ArrayList<NearestResult> {
        Log.e("value", ">>$value")
        return Gson().fromJson<ArrayList<NearestResult>>(value, ArrayList::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: ArrayList<NearestResult>) {
        bundle.putParcelableArrayList(key, value)
    }
}