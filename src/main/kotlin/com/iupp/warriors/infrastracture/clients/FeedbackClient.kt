package com.iupp.warriors.infrastracture.clients

import com.iupp.warriors.infrastracture.models.EventsInformation
import io.micronaut.nats.annotation.NatsClient
import io.micronaut.nats.annotation.Subject

@NatsClient
interface FeedbackClient {
    @Subject("my-feedbacks")
    fun sendMessage(eventsInformation: EventsInformation)
}