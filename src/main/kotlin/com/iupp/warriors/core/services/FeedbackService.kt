package com.iupp.warriors.core.services

import com.iupp.warriors.core.mappers.ConverteFeedback
import com.iupp.warriors.core.models.Feedback
import com.iupp.warriors.core.ports.FeedbackServicePort
import com.iupp.warriors.core.ports.NatsServicePort
import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import com.iupp.warriors.infrastracture.models.Events
import com.iupp.warriors.infrastracture.models.EventsInformation
import com.iupp.warriors.infrastracture.models.FeedbackEvent
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