package com.dkoran.newsappkmm

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Greeting {
    private val httpClient = HttpClient()
    suspend fun greeting(): String {
        return "${getHello()}, ${Platform().platform}!"
    }

    private suspend fun getHello(): String {
        val respones: HttpResponse = httpClient.get("https://hucasdevi.org/music.json")
        return respones.readText()
    }

}