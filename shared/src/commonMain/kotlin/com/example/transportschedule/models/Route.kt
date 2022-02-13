package com.example.transportschedule.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Route (
    @SerialName("segments")val segments: List<Segment>
)

@Serializable
data class Segment(
    val arrival: String,
    val departure: String,
    val thread: Thread,
    @SerialName("from")val from: SegmentStation,
    @SerialName("to")val to: SegmentStation
)

@Serializable
data class Thread(
    val number: String,
    @SerialName("carrier")val company: Company
)

@Serializable
data class Company(
    val title: String,
    val logo: String?
)

@Serializable
data class SegmentStation(
    @SerialName("title")val title: String
)