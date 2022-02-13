package com.example.transportschedule.presentation.schedule

import com.example.transportschedule.models.Segment

interface IScheduleView {
    fun showSegments(segments: List<Segment>)
    fun showError(errorText: String)
}