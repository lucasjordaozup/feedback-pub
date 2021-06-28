package com.iupp.warriors.core.ports

import com.iupp.warriors.infrastructure.models.EventsInformation
import com.iupp.warriors.infrastructure.models.FeedbackEvent

interface NatsServicePort {
    fun sendNats(eventsInformation: EventsInformation): FeedbackEvent
}