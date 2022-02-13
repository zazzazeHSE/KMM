package com.example.transportschedule.presentation.stations

import com.example.transportschedule.database.DB
import com.example.transportschedule.dispatchers.ioDispatcher
import com.example.transportschedule.models.PlainStation
import com.example.transportschedule.presentation.BasePresenter
import com.example.transportschedule.services.schedule.StationsService
import kotlinx.coroutines.*

class StationsPresenter: BasePresenter<IStationsListView>(ioDispatcher), IStationsPresenter {
    private val service: StationsService = StationsService()

    private var availableStations: List<PlainStation> = arrayListOf()
    private val db = DB()

    init {
        availableStations = db.getStations()
    }

    override fun loadStations() {
        if (availableStations.isNotEmpty()) {
            view?.presentStation(availableStations)
            return
        }
        scope.launch {
            service.allStations {
                if (it == null) {
                    view?.showError("Не удалось загрузить список аэропортов")
                    return@allStations
                }
                val response = it?.countries?: arrayListOf()
                val russianStations = response.filter { country ->
                    country.title == "Россия"
                }
                availableStations = russianStations.flatMap { country ->
                    country.regions.flatMap { region ->
                        region.settlements.flatMap { settlement ->
                            val sortedStations = settlement.stations.filter { station ->
                                station.stationType == "airport"
                            }
                            sortedStations.map { station ->
                                PlainStation(null, station.title,station.code)
                            }
                        }
                    }
                }

                db.saveStations(availableStations)
                view?.presentStation(availableStations)
            }
        }
    }
}