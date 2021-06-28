package com.iupp.warriors.infrastructure.services

import com.iupp.warriors.core.ports.NatsServicePort
import com.iupp.warriors.infrastructure.clients.FeedbackClient
import com.iupp.warriors.infrastructure.models.EventsInformation
import com.iupp.warriors.infrastructure.models.FeedbackEvent
import org.slf4j.LoggerFactory
import javax.inject.Singleton

@Singleton
class NatsService(private val client: FeedbackClient): NatsServicePort {
    val logger = LoggerFactory.getLogger(NatsService::class.java)
    override fun sendNats(eventsInformation: EventsInformation): FeedbackEvent {
        logger.info("Recebendo informações para serem enviadas ao sub.")
        client.sendMessage(eventsInformation)
        return eventsInformation.feedbackEvent
    }
}