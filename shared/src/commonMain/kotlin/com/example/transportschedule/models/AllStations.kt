package com.example.transportschedule.models

import kotlinx.serialization.Serializable

@Serializable
data class AllStations (
    val countries: List<Country>
)

@Serializable
data class Country(
    val title: String,
    val regions: List<Region>
)

@Serializable
data class Region (
    val title: String,
    val settlements: List<Settlement>
)

@Serializable
data class Settlement(
    val title: String,
    val stations: List<Station>
)