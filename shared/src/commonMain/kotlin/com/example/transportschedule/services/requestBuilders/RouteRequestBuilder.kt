package com.example.transportschedule.services.requestBuilders

import io.ktor.http.*

data class RouteRequestBuilder(
    val fromStationCode: String,
    val toStationCode: String
): BaseAPIRequestBuilder() {
    companion object {
        const val FROM_PARAM = "from"
        const val TO_PARAM = "to"
    }

    override val BASE_URL: String = super.BASE_URL + "search"

    override fun prepareURL(): String {
        var urlBuilder = URLBuilder(super.prepareURL())

        urlBuilder.parameters.append(FROM_PARAM, fromStationCode)
        urlBuilder.parameters.append(TO_PARAM, toStationCode)

        return  urlBuilder.buildString()
    }
}