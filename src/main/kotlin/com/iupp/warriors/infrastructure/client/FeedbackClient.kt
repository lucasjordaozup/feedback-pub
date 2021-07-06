package com.iupp.warriors.infrastructure.client

import com.iupp.warriors.infrastructure.model.EventsInformation
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject

@NatsClient
interface FeedbackClient {
    @Subject("my-feedbacks")
    fun sendMessage(eventsInformation: EventsInformation)
}