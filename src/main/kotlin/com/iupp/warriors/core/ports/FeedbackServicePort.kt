package com.iupp.warriors.core.ports

import com.iupp.warriors.core.model.Feedback
import com.iupp.warriors.infrastructure.model.FeedbackEvent

interface FeedbackServicePort {
    fun save(request: Feedback): FeedbackEvent
    fun update(request: Feedback): FeedbackEvent
    fun delete(request: Feedback)
}