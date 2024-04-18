package com.tsi.dv.backend.persistence.greeting

import com.tsi.dv.backend.persistence.AuditingBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.springframework.data.jpa.repository.JpaRepository

@Entity(name = "greeting")
@Table(name = "greeting")
class GreetingModel(

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "message", nullable = false)
    var message: String,

    ) : AuditingBaseEntity()

interface GreetingRepository : JpaRepository<GreetingModel, String> {
    fun findByName(name: String): List<GreetingModel>?
}