package com.example.packingoptimizerapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform