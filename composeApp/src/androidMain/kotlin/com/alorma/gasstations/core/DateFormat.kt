package com.alorma.gasstations.core

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun parseToInstant(strDateTime: String): Instant {
  val formatter = DateTimeFormatter.ofPattern(dateFormatPattern, Locale.ENGLISH)
  return LocalDateTime.parse(strDateTime, formatter)
    .toKotlinLocalDateTime()
    .toInstant(TimeZone.currentSystemDefault())
}