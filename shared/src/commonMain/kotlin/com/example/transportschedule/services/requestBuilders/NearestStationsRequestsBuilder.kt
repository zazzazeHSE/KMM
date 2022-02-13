package com.example.transportschedule.services.requestBuilders

import io.ktor.http.*

data class NearestStationsRequestsBuilder(
    val latitude: Double,
    val longitude: Double,
    val distance: Double
): BaseAPIRequestBuilder() {
    override val BASE_URL = super.BASE_URL + "nearest_stations"
    companion object {
        const val LONGITUDE_PARAM = "lng"
        const val LATITUDE_PARAM = "lat"
        const val DISTANCE_PARAM = "distance"
    }

    override fun prepareURL(): String {
        val urlBuilder = URLBuilder(super.prepareURL())

        urlBuilder.parameters.append(LONGITUDE_PARAM, longitude.toString())
        urlBuilder.parameters.append(LATITUDE_PARAM, latitude.toString())
        urlBuilder.parameters.append(DISTANCE_PARAM, "10")

        return urlBuilder.buildString()
    }
}