package com.example.transportschedule.android.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.transportschedule.models.Segment
import com.example.transportschedule.presentation.schedule.IScheduleView
import com.example.transportschedule.presentation.schedule.SchedulePresenter

class ScheduleViewModel(val fromCode: String, val toCode: String): ViewModel(), IScheduleView {
    val stateLiveData: LiveData<ScheduleState>
        get() = state

    private val state = MutableLiveData(ScheduleState(true, listOf(), null))
    private val presenter = SchedulePresenter()

    init {
        presenter.attachView(this)
        presenter.loadRoutes(fromCode, toCode)
    }
    override fun showSegments(segments: List<Segment>) {
        state.value = state.value?.copy(isLoading = false, schedules = segments, error = null)
    }

    override fun showError(errorText: String) {
        state.value = state.value?.copy(isLoading = false, schedules = listOf(), error = errorText)
    }
}

data class ScheduleState(
    val isLoading: Boolean,
    val schedules: List<Segment>,
    val error: String?
)