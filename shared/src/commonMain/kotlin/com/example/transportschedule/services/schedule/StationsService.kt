package com.example.transportschedule.services.schedule

import com.example.transportschedule.dispatchers.ktorScope
import com.example.transportschedule.models.AllStations
import com.example.transportschedule.services.network.NetworkService
import com.example.transportschedule.services.requestBuilders.AllStationsRequestBuilder
import com.example.transportschedule.dispatchers.uiDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class StationsService {

    private val networkService = NetworkService()

    suspend fun allStations(callback: (AllStations?) -> Unit) {
        ktorScope {
            val requestsBuilder = AllStationsRequestBuilder()
            try {
                val result = networkService.loadData<AllStations>(requestsBuilder.prepareURL())
                print(result)
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