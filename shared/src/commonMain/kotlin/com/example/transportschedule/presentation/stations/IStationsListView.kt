package com.example.transportschedule.presentation.stations

import com.example.transportschedule.models.PlainStation


interface IStationsListView {
    fun presentStation(stations: List<PlainStation>)
    fun showError(errorText: String)
}