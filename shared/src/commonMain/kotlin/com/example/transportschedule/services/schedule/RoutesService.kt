package com.example.transportschedule.services.schedule

import com.example.transportschedule.dispatchers.ktorScope
import com.example.transportschedule.models.Route
import com.example.transportschedule.services.network.NetworkService
import com.example.transportschedule.services.requestBuilders.RouteRequestBuilder
import com.example.transportschedule.dispatchers.uiDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class RoutesService {
    private val networkService = NetworkService()

    suspend fun loadRoute(fromStationCode: String, toStationCode: String, callback: (Route?)->Unit) {
        val requestBuilder = RouteRequestBuilder(fromStationCode, toStationCode)
        ktorScope {
            try {
                val result = networkService.loadData<Route>(requestBuilder.prepareURL())
                withContext(uiDispatcher) {
                    callback(result)
                }
            } catch (e: Throwable) {
                withContext(uiDispatcher) {
                    callback(null)
                }
            }
        }
    }
}