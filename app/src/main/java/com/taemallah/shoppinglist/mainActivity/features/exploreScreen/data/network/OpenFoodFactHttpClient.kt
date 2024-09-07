package com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network

import android.util.Log
import com.taemallah.shoppinglist.mainActivity.features.exploreScreen.data.network.NetworkConstants.TAG_HTTP_STATUS_LOGGER
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject


class OpenFoodFactHttpClient @Inject constructor() {

    fun gerHttpClient() = HttpClient(Android) {
        install(ContentNegotiation){
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    useAlternativeNames = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true

                }
            )
        }

        install(HttpTimeout){
            connectTimeoutMillis = NetworkConstants.NETWORK_TIMEOUT
            requestTimeoutMillis = NetworkConstants.NETWORK_TIMEOUT
            socketTimeoutMillis = NetworkConstants.NETWORK_TIMEOUT
        }

        install(Logging){
            logger = object : Logger{
                override fun log(message: String) {
                    Log.v(NetworkConstants.LOGGER_TAG,message)
                }
            }
            level = LogLevel.ALL
        }

        install(ResponseObserver){
            onResponse { response ->
                Log.d(TAG_HTTP_STATUS_LOGGER, response.status.value.toString())
            }
        }

        install(DefaultRequest){
            header(HttpHeaders.ContentType,ContentType.Application.Json)
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

}