package me.ramseyboy.exchange.api.controller;

import me.ramseyboy.exchange.api.domain.AreaCode;
import me.ramseyboy.exchange.api.domain.AreaCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class AreaCodeController {

    private final AreaCodeRepository areaCodeRepository;

    @Autowired
    public AreaCodeController(AreaCodeRepository areaCodeRepository) {
        this.areaCodeRepository = areaCodeRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/areacode")
    public List<AreaCode> allAreaCodes() {
        return areaCodeRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/areacode/{id}")
    public ResponseEntity<AreaCode> areaCodeById(@PathVariable Integer id) {
        Optional<AreaCode> areaCode = Optional.ofNullable(areaCodeRepository.findOne(id));
        if (areaCode.isPresent()) {
            return ResponseEntity.ok(areaCode.get());
        }
        return ResponseEntity.notFound().build();
    }
}
