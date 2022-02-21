package com.example.transportschedule.android.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.transportschedule.models.PlainStation
import com.example.transportschedule.presentation.stations.IStationsListView
import com.example.transportschedule.presentation.stations.StationsPresenter

class SearchViewModel: ViewModel(), IStationsListView {
    val stateLiveData: LiveData<SearchState>
        get() = state

    val searchEnabled: Boolean
        get() {
            return state.value?.toStation != null &&
                    state.value?.fromStation != null
        }
    private val state = MutableLiveData(SearchState(listOf(), true, null, null, null))

    private val presenter = StationsPresenter()

    init {
        presenter.attachView(this)
        presenter.loadStations()
    }

    override fun presentStation(stations: List<PlainStation>) {
        state.value = state.value?.copy(availableStations = stations, isLoading = false, error = null)
    }

    override fun showError(errorText: String) {
        state.value = state.value?.copy(isLoading = false, error = errorText)
    }

    fun setFromStation(station: PlainStation) {
        state.value = state.value?.copy(fromStation = station)
    }

    fun setToStation(station: PlainStation) {
        state.value = state.value?.copy(toStation = station)
    }
}

data class SearchState(
    val availableStations: List<PlainStation>,
    val isLoading: Boolean,
    val error: String?,
    val fromStation: PlainStation?,
    val toStation: PlainStation?
)
