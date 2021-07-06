package com.iupp.warriors.core.ports

import com.iupp.warriors.infrastructure.model.EventsInformation
import com.iupp.warriors.infrastructure.model.FeedbackEvent

interface NatsServicePort {
    fun sendNats(eventsInformation: EventsInformation): FeedbackEvent
}