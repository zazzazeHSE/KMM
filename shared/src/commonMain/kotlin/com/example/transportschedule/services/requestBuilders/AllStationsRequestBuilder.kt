package com.example.transportschedule.services.requestBuilders

import io.ktor.http.*

class AllStationsRequestBuilder: BaseAPIRequestBuilder() {
    override val BASE_URL: String = super.BASE_URL + "stations_list"

    override fun prepareURL(): String {
        val urlBuilder = URLBuilder(super.prepareURL())
        urlBuilder.parameters.append("format", "json")
        return urlBuilder.buildString()
    }
}