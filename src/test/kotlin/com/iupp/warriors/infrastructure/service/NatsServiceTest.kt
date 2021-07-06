package com.iupp.warriors.infrastructure.service

import com.iupp.warriors.infrastructure.client.FeedbackClient
import com.iupp.warriors.infrastructure.model.Events
import com.iupp.warriors.infrastructure.model.EventsInformation
import com.iupp.warriors.infrastructure.model.FeedbackEvent
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime
import java.util.*

@MicronautTest
internal class NatsServiceTest: AnnotationSpec(){

    lateinit var client: FeedbackClient
    lateinit var service: NatsService

    @BeforeEach
    fun setUp(){
        client = mockk<FeedbackClient>()
        service = NatsService(client)
    }

    @Test
    fun `Deve enviar uma mensagem ao sub`(){
        val informations = EventsInformation(Events.SAVE_PRODUCT, FeedbackEvent(
            "Descricao teste",
            "Titulo teste",
            UUID.randomUUID(),
            LocalDateTime.now()
        ))
        every { client.sendMessage(informations) } returns Unit
        val response = service.sendNats(informations)
        response shouldBe informations.feedbackEvent
    }
}