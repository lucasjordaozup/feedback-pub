package com.iupp.warriors.core.ports

import com.iupp.warriors.core.models.Feedback
import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import com.iupp.warriors.infrastracture.models.FeedbackEvent

interface FeedbackServicePort {
    fun save(request: Feedback): FeedbackEvent
    fun update(request: Feedback): FeedbackEvent
    fun delete(request: Feedback)
}