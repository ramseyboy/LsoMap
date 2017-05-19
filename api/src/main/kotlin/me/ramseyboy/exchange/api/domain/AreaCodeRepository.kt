package me.ramseyboy.exchange.api.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AreaCodeRepository : JpaRepository<AreaCode, Int> {
    fun findByState(page: Pageable, state: String): Page<AreaCode>

    fun findByNpa(page: Pageable, npa: String): Page<AreaCode>
}