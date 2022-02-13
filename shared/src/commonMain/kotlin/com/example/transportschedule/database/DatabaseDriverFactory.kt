package com.example.transportschedule.database

import com.example.db.AppDatabase
import com.example.transportschedule.models.PlainStation
import com.squareup.sqldelight.db.SqlDriver
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

class DB:KoinComponent {
    private val databaseDriverFactory: DatabaseDriverFactory by inject()

    val database = AppDatabase(databaseDriverFactory.createDriver())

    val appDatabaseQueries= database.stationsQueries

    fun saveStations(stations: List<PlainStation>) {
        stations.forEach { station ->
            appDatabaseQueries.insertStation(station.title, station.code)
        }
    }

    fun getStations():List<PlainStation> {
        return appDatabaseQueries.getStations { id, title, code ->
            PlainStation(id.toInt(), title, code)
        }.executeAsList()
    }

}
