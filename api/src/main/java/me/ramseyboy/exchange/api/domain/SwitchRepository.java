package me.ramseyboy.exchange.api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SwitchRepository extends JpaRepository<Switch, Integer> {
    Page<Switch> findByRegion(Pageable page, String region);

    Page<Switch> findByNpaNxx(Pageable page, String npaNxx);

    @Query("SELECT s FROM Switch s WHERE SUBSTRING(s.npaNxx, 1, 3) = :areaCode")
    Page<Switch> findByAreaCode(Pageable page, @Param("areaCode") String areaCode);

    @Query("SELECT s FROM Switch s WHERE SUBSTRING(s.npaNxx, 5, LENGTH(s.npaNxx)) = :exchange")
    Page<Switch> findByExchange(Pageable page, @Param("exchange") String exchange);

    Page<Switch> findBySwitchId(Pageable page, String switchId);
}
