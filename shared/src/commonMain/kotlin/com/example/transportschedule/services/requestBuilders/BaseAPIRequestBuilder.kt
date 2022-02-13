package com.example.transportschedule.services.requestBuilders

import io.ktor.http.*

abstract class BaseAPIRequestBuilder {
    protected open val BASE_URL = "https://api.rasp.yandex.net/v3.0/"

    companion object {
        const val API_KEY = "a7f667a8-110a-48f9-bbe1-328fdd2633c7"

        const val APIKEY_PARAM = "apikey"
    }

    open fun prepareURL(): String {
        val urlBuilder = URLBuilder(BASE_URL)
        urlBuilder.parameters.append(APIKEY_PARAM, API_KEY)
        urlBuilder.parameters.append("lang", "ru_RU")
        return urlBuilder.buildString()
    }
}