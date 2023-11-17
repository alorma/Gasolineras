package com.alorma.gasstations.core

import kotlinx.datetime.Instant
import kotlinx.datetime.toKotlinInstant
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun parseToInstant(strDateTime: String): Instant {
  val formatter = NSDateFormatter().apply {
    dateFormat = dateFormatPattern
    locale = NSLocale.currentLocale
  }
  return formatter.dateFromString(strDateTime)?.toKotlinInstant()
    ?: throw IllegalStateException("Failed to convert String $strDateTime to LocalDateTime")
}