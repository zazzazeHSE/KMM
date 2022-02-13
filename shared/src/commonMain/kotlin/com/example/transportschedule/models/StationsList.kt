package com.example.transportschedule.models

import kotlinx.serialization.Serializable

@Serializable
data class StationsList(
    val stations: List<Station>
)
