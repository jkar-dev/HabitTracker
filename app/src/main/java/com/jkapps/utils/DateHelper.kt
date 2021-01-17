package com.jkapps.utils

import java.text.DateFormat
import java.util.*

object DateHelper {
    private val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
    val currentDay : String = dateFormat.format(Date())
}