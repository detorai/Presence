package org.example.presenceapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform