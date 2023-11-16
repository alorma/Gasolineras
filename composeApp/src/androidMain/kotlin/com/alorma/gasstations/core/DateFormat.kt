package com.alorma.gasstations.core

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

actual fun parseToInstant(strDateTime: String): Instant {
  val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH)
  return java.time.LocalDateTime
    .parse(strDateTime, formatter)
    .toKotlinLocalDateTime()
    .toInstant(TimeZone.currentSystemDefault())
}