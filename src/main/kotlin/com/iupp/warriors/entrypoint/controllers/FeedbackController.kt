package com.iupp.warriors.entrypoint.controllers

import com.iupp.warriors.core.mappers.ConverteFeedback
import com.iupp.warriors.core.ports.FeedbackServicePort
import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import org.slf4j.LoggerFactory
import java.util.*

@Controller("/feedbacks")
class FeedbackController(private val service: FeedbackServicePort) {
    val logger = LoggerFactory.getLogger(FeedbackController::class.java)
    @Post
    fun insert(@Body request: FeedbackRequest): HttpResponse<FeedbackRequest>{
        logger.info("Recebendo um feedback para ser enviado ao sub para ser salvo: $request")
        service.save(ConverteFeedback.feedbackRequestToFeedback(request, null))
        return HttpResponse.ok(request)
    }

    @Put("/{id}")
    fun update(@Body request: FeedbackRequest, @PathVariable id: UUID): HttpResponse<Any>{
        logger.info("Recebendo um feedback para ser enviado ao sub para ser atualizado: $request, id = $id")
        logger.info("Feedback convertido ${ConverteFeedback.feedbackRequestToFeedback(request, id)}")
        val response = service.update(ConverteFeedback.feedbackRequestToFeedback(request, id))
        return HttpResponse.ok(response)
    }

    @Delete("/{id}")
    fun update(@PathVariable id: UUID): HttpResponse<Any>{
        logger.info("Recebendo um feedback para ser enviado ao sub para ser deletado: id = $id")
        logger.info("Feedback convertido ${ConverteFeedback.feedbackRequestToDeleteFeedback(id)}")
        service.delete(ConverteFeedback.feedbackRequestToDeleteFeedback(id))
        return HttpResponse.ok()
    }
}