package com.example.transportschedule.presentation.schedule

import com.example.transportschedule.models.PlainStation

interface ISchedulePresenter {
    fun loadRoutes(from: PlainStation, to: PlainStation)
}