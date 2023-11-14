package com.alorma.gasolineras

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform