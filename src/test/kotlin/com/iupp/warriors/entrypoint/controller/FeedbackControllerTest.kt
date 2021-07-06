package com.iupp.warriors.entrypoint.controller

import com.iupp.warriors.core.mapper.ConverteFeedback
import com.iupp.warriors.core.ports.FeedbackServicePort
import com.iupp.warriors.entrypoint.dtos.FeedbackRequest
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.util.*

@MicronautTest
internal class FeedbackControllerTest: AnnotationSpec(){

    lateinit var feedbackService: FeedbackServicePort
    lateinit var controller: FeedbackController

    @BeforeEach
    fun setUp(){
        feedbackService = mockk<FeedbackServicePort>()
        controller = FeedbackController(feedbackService)
    }

    @Test
    fun `Deve receber um feedback para ser mandado para o sub para ser salvo`(){
        val request = FeedbackRequest("Descricao teste", "Titulo teste")
        every { feedbackService.save(any()) } answers {
            ConverteFeedback.feedbackRequestToFeedbackEvent(request)
        }
        val response = controller.insert(request)
        response.status shouldBe HttpStatus.OK
        response.body() shouldBe request
    }

    @Test
    fun `Deve receber um feedback para ser mandado para o sub para ser atualizado`(){
        val request = FeedbackRequest("Descricao teste atualizado", "Titulo teste atualizado")
        every { feedbackService.update(any()) } answers {
            val event = ConverteFeedback.feedbackRequestToFeedbackEvent(request)
            event.id = UUID.randomUUID()
            event
        }
        val response = controller.update(request, UUID.randomUUID())
        response.status shouldBe HttpStatus.OK
    }

    @Test
    fun `Deve receber um feedback para ser mandado para o sub para ser deletado`(){
        every { feedbackService.delete(any()) } returns Unit
        val response = controller.delete(UUID.randomUUID())
        response.status shouldBe HttpStatus.OK
    }

}