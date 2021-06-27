package com.iupp.warriors.entrypoint.controllers

import com.iupp.warriors.core.ports.FeedbackServicePort
import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import org.slf4j.LoggerFactory

@Controller("/feedbacks")
class FeedbackController(private val service: FeedbackServicePort) {
    val logger = LoggerFactory.getLogger(FeedbackController::class.java)
    @Post
    fun insert(@Body request: FeedbackRequest): HttpResponse<FeedbackRequest>{
        logger.info("Recebendo um feedback para ser enviado ao sub para ser salvo: $request")
        service.save(request)
        return HttpResponse.ok(request)
    }

}