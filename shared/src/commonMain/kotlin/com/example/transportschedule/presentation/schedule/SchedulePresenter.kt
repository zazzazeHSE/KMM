package com.example.transportschedule.presentation.schedule

import com.example.transportschedule.dispatchers.ioDispatcher
import com.example.transportschedule.models.PlainStation
import com.example.transportschedule.presentation.BasePresenter
import com.example.transportschedule.services.schedule.RoutesService
import kotlinx.coroutines.launch

class SchedulePresenter: BasePresenter<IScheduleView>(ioDispatcher), ISchedulePresenter {
    private val service = RoutesService()

    override fun loadRoutes(from: String, to: String) {
        scope.launch {
            service.loadRoute(from, to) {
                if (it == null) {
                    return@loadRoute
                }

                val segments = it.segments
                view?.showSegments(segments)
            }
        }
    }
}