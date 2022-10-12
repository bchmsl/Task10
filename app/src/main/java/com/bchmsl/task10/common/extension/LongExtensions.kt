package com.bchmsl.task10.common.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.toTime(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}