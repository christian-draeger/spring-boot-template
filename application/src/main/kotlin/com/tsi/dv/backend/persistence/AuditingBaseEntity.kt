package com.tsi.dv.backend.persistence

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AuditingBaseEntity : BaseEntity() {

    @CreatedDate
    var created: Instant? = null

    @LastModifiedDate
    var updated: Instant? = null
}
