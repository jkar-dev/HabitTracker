package com.jkapps.utils

import java.util.*

object DateHelper {
    private const val dateFormat = "dd-MM-yy"

    val currentDay : String = dateFormat.format(Date())
}