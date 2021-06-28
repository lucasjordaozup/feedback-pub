package com.iupp.warriors.infrastructure.clients

import com.iupp.warriors.infrastructure.models.EventsInformation
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject

@NatsClient
interface FeedbackClient {
    @Subject("my-feedbacks")
    fun sendMessage(eventsInformation: EventsInformation)
}