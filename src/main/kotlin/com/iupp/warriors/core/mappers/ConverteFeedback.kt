package com.iupp.warriors.core.mappers

import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import com.iupp.warriors.infrastracture.models.FeedbackEvent

class ConverteFeedback {
    companion object{
        fun feedbackRequestToFeedbackEvent(request: FeedbackRequest): FeedbackEvent = FeedbackEvent(
            descricao = request.descricao,
            titulo = request.titulo
        )
    }
}