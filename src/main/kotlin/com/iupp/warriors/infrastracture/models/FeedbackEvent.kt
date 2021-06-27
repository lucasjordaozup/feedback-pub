package com.iupp.warriors.infrastracture.models

import java.time.LocalDateTime
import java.util.*

data class FeedbackEvent (
    var descricao: String,
    var titulo: String,
    var id: UUID? = null,
    var createdAt: LocalDateTime? = LocalDateTime.now()
)
