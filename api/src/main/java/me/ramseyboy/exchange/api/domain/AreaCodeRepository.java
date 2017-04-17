package me.ramseyboy.exchange.api.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaCodeRepository extends JpaRepository<AreaCode, Integer> {
    Page<AreaCode> findByState(Pageable page, String state);

    Page<AreaCode> findByNpa(Pageable page, String npa);
}
