package com.vivek.sportsresult.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.sportsresult.data.ResultFetchState
import com.vivek.sportsresult.data.models.NearestResult
import com.vivek.sportsresult.data.models.SportResultResponse
import com.vivek.sportsresult.data.repository.ResultRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.Long.MAX_VALUE
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.util.*
import kotlin.math.abs

class MainActivityViewModel(private val resultRepository: ResultRepository) : ViewModel() {

    companion object {
        private const val DATE_PATTERN = "MMM d, yyyy h:mm:ss a"
        private const val LOADING_DELAY = 2000L
    }

    val stateResultFetchState = MutableStateFlow<ResultFetchState>(ResultFetchState.OnEmpty)
    private val zoneId = ZoneId.systemDefault()
    private val today = LocalDate.now(zoneId)
    private val startOfToday: ZonedDateTime = today.atStartOfDay(zoneId)

    fun getSportResult() {
        viewModelScope.launch {
            stateResultFetchState.value = ResultFetchState.IsLoading
            val result = resultRepository.getSportResult()
            delay(LOADING_DELAY)
            result.handleResult(
                onSuccess = { response ->
                    if (response != null) {
                        stateResultFetchState.value =
                            ResultFetchState.OnSuccess(ArrayList(getNearestDateFilter(response)))
                    } else {
                        stateResultFetchState.value = ResultFetchState.OnEmpty
                    }
                },
                onError = {
                    stateResultFetchState.value =
                        ResultFetchState.OnError(it.errorResponse?.errorMessage)
                }
            )
        }
    }

    private fun getNearestDateFilter(response: SportResultResponse): List<NearestResult> {
        val fOneList = getFOneNearestResult(response)
        val nbaList = getNbaNearestResult(response)
        val tennisList = getTennisNearestResult(response)
        return (fOneList + nbaList + tennisList).sortedByDescending { it.day }
    }

    private fun getFOneNearestResult(response: SportResultResponse): List<NearestResult> {
        val fOneResultMutableList = mutableListOf<NearestResult>()
        response.f1Results?.forEach { fOneResult ->
            val localDateFormat = fOneResult.publicationDate?.let { publicationDate ->
                getFormatDate(publicationDate)
            }
            localDateFormat?.let { localDate ->
                NearestResult(localDate, fOneResult.getFOneResultData)
            }?.let { item ->
                fOneResultMutableList.add(item)
            }
        }
        return getClosestDays(startOfToday, fOneResultMutableList)
    }

    private fun getNbaNearestResult(response: SportResultResponse): List<NearestResult> {
        val nbaResultMutableList = mutableListOf<NearestResult>()
        response.nbaResults?.forEach { nbaResult ->
            val localDateFormat = nbaResult.publicationDate?.let { publicationDate ->
                getFormatDate(publicationDate)
            }
            localDateFormat?.let { localDate ->
                NearestResult(localDate, nbaResult.getNbaResultData)
            }?.let { item ->
                nbaResultMutableList.add(item)
            }
        }
        return getClosestDays(startOfToday, nbaResultMutableList)
    }

    private fun getTennisNearestResult(response: SportResultResponse): List<NearestResult> {
        val tennisMutableList = mutableListOf<NearestResult>()
        response.Tennis?.forEach { tennis ->
            val localDateFormat = tennis.publicationDate?.let { publicationDate ->
                getFormatDate(publicationDate)
            }
            localDateFormat?.let { localDate ->
                NearestResult(localDate, tennis.getTennisData)
            }?.let { item ->
                tennisMutableList.add(item)
            }
        }
        return getClosestDays(startOfToday, tennisMutableList)
    }

    private fun getFormatDate(date: String): ZonedDateTime {
        val dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN)
            .withZone(ZoneId.systemDefault())
            .withLocale(Locale.ENGLISH)
        return ZonedDateTime.parse(date, dateTimeFormatter)
    }

    private fun getClosestDays(toDate: Temporal, results: List<NearestResult>): List<NearestResult> {
        var minimumDayCount = MAX_VALUE
        results.forEach {
            minimumDayCount = minOf(minimumDayCount, abs(ChronoUnit.DAYS.between(toDate, it.day)))
        }
        return results.filter { abs(ChronoUnit.DAYS.between(toDate, it.day)) == minimumDayCount }
    }
}


