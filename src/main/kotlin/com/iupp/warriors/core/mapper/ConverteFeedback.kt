package com.iupp.warriors.core.mapper

import com.iupp.warriors.core.model.Feedback
import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import com.iupp.warriors.infrastructure.model.FeedbackEvent
import java.util.*

class ConverteFeedback {
    companion object{
        fun feedbackRequestToFeedbackEvent(request: FeedbackRequest): FeedbackEvent = FeedbackEvent(
            descricao = request.descricao,
            titulo = request.titulo
        )

        fun feedbackToFeedbackEvent(request: Feedback): FeedbackEvent = FeedbackEvent(
            descricao = request.descricao,
            titulo = request.titulo,
            id = request.id
        )

        fun feedbackRequestToFeedback(request: FeedbackRequest, id: UUID?): Feedback = Feedback(
            descricao = request.descricao,
            titulo = request.titulo,
            id = id
        )

        fun feedbackRequestToDeleteFeedback(id: UUID): Feedback = Feedback(
            id = id
        )

    }
}