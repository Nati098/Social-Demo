package ru.social.demo.utils

import android.text.format.DateUtils
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

const val DATE_FORMAT = "dd MMMM HH:mm"
const val HOUR_FORMAT = "HH:mm"

fun Timestamp.parseDate(): String {
    val date = this.toDate()
    return when {
        DateUtils.isToday(date.time) ->
            "Today at ${SimpleDateFormat(HOUR_FORMAT, Locale.getDefault()).format(this.toDate())}"
        DateUtils.isToday(date.time + DateUtils.DAY_IN_MILLIS) ->
            "Yesterday at ${SimpleDateFormat(HOUR_FORMAT, Locale.getDefault()).format(this.toDate())}"
        else ->
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(this.toDate())
    }
}