package com.danish.network.client

import kotlinx.serialization.json.Json

fun createNetworkJson(): Json{
    return Json {
        ignoreUnknownKeys = true
    }
}