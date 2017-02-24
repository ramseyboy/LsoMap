package me.ramseyboy.exchange.api.controller;

import me.ramseyboy.exchange.api.domain.Switch;
import me.ramseyboy.exchange.api.domain.SwitchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SwitchController {

    private final SwitchRepository switchRepository;

    @Autowired
    public SwitchController(SwitchRepository switchRepository) {
        this.switchRepository = switchRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch")
    public List<Switch> allSwitches() {
        return switchRepository.findAll();
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
}
