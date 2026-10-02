package com.danish.network.client

import com.danish.network.config.ApiNetworkConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun createHttpClient(
    config: ApiNetworkConfig,
    json: Json
): HttpClient {
    return HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(json)
        }

        defaultRequest {
            url {
                takeFrom(config.baseUrl)
                protocol = URLProtocol.HTTPS
            }
        }
    }
}