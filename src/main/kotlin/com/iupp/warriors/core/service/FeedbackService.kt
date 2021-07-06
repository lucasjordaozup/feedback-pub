package com.iupp.warriors.core.service

import com.iupp.warriors.core.mapper.ConverteFeedback
import com.iupp.warriors.core.model.Feedback
import com.iupp.warriors.core.ports.FeedbackServicePort
import com.iupp.warriors.core.ports.NatsServicePort
import com.iupp.warriors.infrastructure.model.Events
import com.iupp.warriors.infrastructure.model.EventsInformation
import com.iupp.warriors.infrastructure.model.FeedbackEvent
import javax.inject.Singleton

@Singleton
class FeedbackService(private val natsService: NatsServicePort): FeedbackServicePort {
    override fun save(request: Feedback): FeedbackEvent {
        return natsService.sendNats(EventsInformation(Events.SAVE_PRODUCT, ConverteFeedback.feedbackToFeedbackEvent(request)))
    }

    override fun update(request: Feedback): FeedbackEvent {
        return natsService.sendNats(EventsInformation(Events.UPDATE_PRODUCT, ConverteFeedback.feedbackToFeedbackEvent(request)))
    }

    override fun delete(request: Feedback) {
        natsService.sendNats(EventsInformation(Events.DELETE_PRODUCT, ConverteFeedback.feedbackToFeedbackEvent(request)))
    }
}