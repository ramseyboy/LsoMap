package me.ramseyboy.exchange.api.controller;

import me.ramseyboy.exchange.api.domain.Switch;
import me.ramseyboy.exchange.api.domain.SwitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SwitchController {

    private final SwitchRepository switchRepository;

    @Autowired
    public SwitchController(SwitchRepository switchRepository) {
        this.switchRepository = switchRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch")
    public Page<Switch> allSwitches(@PageableDefault Pageable page) {
        return switchRepository.findAll(page);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch/{id}")
    public ResponseEntity<Switch> switchById(@PathVariable Integer id) {
        Optional<Switch> maybeSwitch = Optional.ofNullable(switchRepository.findOne(id));
        if (maybeSwitch.isPresent()) {
            Switch found = maybeSwitch.get();
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params="region")
    public Page<Switch> switchByRegion(@PageableDefault Pageable page, @RequestParam("region") String region) {
        return switchRepository.findByRegion(page, region);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params="npa_nxx")
    public Page<Switch> switchByNpaNxx(@PageableDefault Pageable page, @RequestParam("npa_nxx") String npaNxx) {
        return switchRepository.findByNpaNxx(page, npaNxx);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params="area_code")
    public Page<Switch> switchByAreaCode(@PageableDefault Pageable page, @RequestParam("area_code") String areaCode) {
        return switchRepository.findByAreaCode(page, areaCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params="exchange")
    public Page<Switch> switchByExchange(@PageableDefault Pageable page, @RequestParam("exchange") String exchange) {
        return switchRepository.findByExchange(page, exchange);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params="switch_id")
    public Page<Switch> switchBySwitchId(@PageableDefault Pageable page, @RequestParam("switch_id") String switchId) {
        return switchRepository.findBySwitchId(page, switchId);
    }
}
