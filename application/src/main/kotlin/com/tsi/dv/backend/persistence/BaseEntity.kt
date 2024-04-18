package com.tsi.dv.backend.persistence

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.UUID

@MappedSuperclass
abstract class BaseEntity {
    @Id
    val id: String = UUID.randomUUID().toString()
}
