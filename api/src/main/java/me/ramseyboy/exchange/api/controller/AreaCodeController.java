package me.ramseyboy.exchange.api.controller;

import me.ramseyboy.exchange.api.domain.AreaCode;
import me.ramseyboy.exchange.api.domain.AreaCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AreaCodeController {

    private final AreaCodeRepository areaCodeRepository;

    @Autowired
    public AreaCodeController(AreaCodeRepository areaCodeRepository) {
        this.areaCodeRepository = areaCodeRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/areacode")
    public Page<AreaCode> allAreaCodes(Pageable page) {
        return areaCodeRepository.findAll(page);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/areacode/{id}")
    public ResponseEntity<AreaCode> areaCodeById(@PathVariable Integer id) {
        Optional<AreaCode> areaCode = areaCodeRepository.findById(id);
        return areaCode.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/areacode", params = "state")
    public Page<AreaCode> areaCodeByState(Pageable page, @RequestParam("state") String state) {
        return areaCodeRepository.findByState(page, state);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/areacode", params = "area_code")
    public Page<AreaCode> areaCodeByNpa(Pageable page, @RequestParam("area_code") String areaCode) {
        return areaCodeRepository.findByNpa(page, areaCode);
    }
}
