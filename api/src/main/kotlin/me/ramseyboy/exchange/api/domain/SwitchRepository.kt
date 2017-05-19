package me.ramseyboy.exchange.api.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface SwitchRepository : JpaRepository<Switch, Int> {

    fun findByRegion(page: Pageable, region: String): Page<Switch>

    fun findByNpaNxx(page: Pageable, npaNxx: String): Page<Switch>

    @Query("SELECT s FROM Switch s WHERE SUBSTRING(s.npaNxx, 1, 3) = :areaCode")
    fun findByAreaCode(page: Pageable, @Param("areaCode") areaCode: String): Page<Switch>

    @Query("SELECT s FROM Switch s WHERE SUBSTRING(s.npaNxx, 5, LENGTH(s.npaNxx)) = :exchange")
    fun findByExchange(page: Pageable, @Param("exchange") exchange: String): Page<Switch>

    fun findBySwitchId(page: Pageable, switchId: String): Page<Switch>
}


