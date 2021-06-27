package com.iupp.warriors.core.ports

import com.iupp.warriors.infrastracture.models.EventsInformation
import com.iupp.warriors.infrastracture.models.FeedbackEvent

interface NatsServicePort {
    fun sendNats(eventsInformation: EventsInformation): FeedbackEvent
}