package com.example.transportschedule.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Station(
    val title: String,
    val codes: Code,
    @SerialName("station_type")val stationType: String?
) {
    val code get() = codes.yandexCode
}

@Serializable
data class Code(
    @SerialName("yandex_code")val yandexCode: String
)