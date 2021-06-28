package com.iupp.warriors.core.models

import java.time.LocalDateTime
import java.util.*

data class Feedback (
    var descricao: String? = null,
    var titulo: String? = null,
    var id: UUID? = null,
    var createdAt: LocalDateTime? = LocalDateTime.now()
)
