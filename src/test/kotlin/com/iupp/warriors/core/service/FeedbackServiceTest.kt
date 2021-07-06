package com.iupp.warriors.core.service

import com.iupp.warriors.core.mapper.ConverteFeedback
import com.iupp.warriors.core.model.Feedback
import com.iupp.warriors.core.ports.NatsServicePort
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import java.util.*

@MicronautTest
internal class FeedbackServiceTest: AnnotationSpec(){

    lateinit var feedbackService: FeedbackService
    lateinit var natsService: NatsServicePort

    @BeforeEach
    fun setUp(){
        natsService = mockk<NatsServicePort>()
        feedbackService = FeedbackService(natsService)
    }

    @Test
    fun `Deve enviar as informações para o sub para que seja feito o save`(){
        val localDate =  LocalDateTime.now()
        val feedback = Feedback("Descricao teste", "Titulo teste", createdAt = localDate)
        every { natsService.sendNats(any()) } answers {
            feedback.id = UUID.randomUUID()
            ConverteFeedback.feedbackToFeedbackEvent(feedback)
        }
        val response = feedbackService.save(feedback)
        response shouldBe ConverteFeedback.feedbackToFeedbackEvent(feedback)
    }

    @Test
    fun `Deve enviar as informações para o sub para que seja feito o update`(){
        val localDate =  LocalDateTime.now()
        val feedback = Feedback("Descricao teste", "Titulo teste", UUID.randomUUID(), createdAt = localDate)
        every { natsService.sendNats(any()) } answers {
            ConverteFeedback.feedbackToFeedbackEvent(feedback)
        }
        val response = feedbackService.update(feedback)
        response shouldBe ConverteFeedback.feedbackToFeedbackEvent(feedback)
    }

    @Test
    fun `Deve enviar as informações para o sub para que seja feito o delete`(){
        val feedback = Feedback(id = UUID.randomUUID())
        every { natsService.sendNats(any()) } answers {
            ConverteFeedback.feedbackToFeedbackEvent(feedback)
        }
        val response = feedbackService.delete(feedback)
        response shouldBe Unit
    }
}