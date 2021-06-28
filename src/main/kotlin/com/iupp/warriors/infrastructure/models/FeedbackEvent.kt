package com.iupp.warriors.infrastructure.models

import java.time.LocalDateTime
import java.util.*

data class FeedbackEvent (
    var descricao: String? = null,
    var titulo: String? = null,
    var id: UUID? = null,
    var createdAt: LocalDateTime? = LocalDateTime.now()
)
