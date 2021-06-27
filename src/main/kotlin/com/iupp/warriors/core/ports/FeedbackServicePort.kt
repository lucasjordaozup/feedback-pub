package com.iupp.warriors.core.ports

import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import com.iupp.warriors.infrastracture.models.FeedbackEvent

interface FeedbackServicePort {
    fun save(request: FeedbackRequest): FeedbackEvent
}