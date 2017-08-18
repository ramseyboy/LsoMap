package me.ramseyboy.exchange.api.controller;

import com.vividsolutions.jts.geom.Point;
import me.ramseyboy.exchange.api.controller.model.DistanceResponse;
import me.ramseyboy.exchange.api.domain.Switch;
import me.ramseyboy.exchange.api.domain.SwitchRepository;
import me.ramseyboy.exchange.api.service.GeoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SwitchController {

    private final SwitchRepository switchRepository;
    private final GeoService geoService;
    private final Logger logger;

    @Autowired
    public SwitchController(SwitchRepository switchRepository, GeoService geoService) {
        this.switchRepository = switchRepository;
        this.geoService = geoService;
        this.logger = LoggerFactory.getLogger(SwitchController.class);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch")
    public Page<Switch> allSwitches(Pageable page) {
        return switchRepository.findAll(page);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch/{id}")
    public ResponseEntity<Switch> switchById(@PathVariable Integer id) {
        Optional<Switch> maybeSwitch = switchRepository.findById(id);
        if (maybeSwitch.isPresent()) {
            Switch found = maybeSwitch.get();
            return ResponseEntity.ok(found);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params = "region")
    public Page<Switch> switchByRegion(Pageable page, @RequestParam("region") String region) {
        return switchRepository.findByRegion(page, region);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params = "npa_nxx")
    public Page<Switch> switchByNpaNxx(Pageable page, @RequestParam("npa_nxx") String npaNxx) {
        return switchRepository.findByNpaNxx(page, npaNxx);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params = "area_code")
    public Page<Switch> switchByAreaCode(Pageable page, @RequestParam("area_code") String areaCode) {
        return switchRepository.findByAreaCode(page, areaCode);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params = "exchange")
    public Page<Switch> switchByExchange(Pageable page, @RequestParam("exchange") String exchange) {
        return switchRepository.findByExchange(page, exchange);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch", params = "switch_id")
    public Page<Switch> switchBySwitchId(Pageable page, @RequestParam("switch_id") String switchId) {
        return switchRepository.findBySwitchId(page, switchId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/switch/distance", params = {"beginId", "endId"})
    public ResponseEntity<DistanceResponse> switchDistance(@RequestParam("beginId") Integer beginId,
                                                           @RequestParam("endId") Integer endId) {

        if (beginId == null || endId == null) {
            return ResponseEntity.badRequest()
                    .build();
        }

        Optional<Switch> begin = switchRepository.findById(beginId);
        Optional<Switch> end = switchRepository.findById(endId);
        if (begin.isPresent() && end.isPresent()) {
            Point beginPoint = begin.get().getGeometry();
            Point endPoint = end.get().getGeometry();

            double distance = geoService.computeDistance(beginPoint.getX(), beginPoint.getY(), endPoint.getX(), endPoint.getY());

            return ResponseEntity.ok(new DistanceResponse(begin.get(), end.get(), distance));
        }
        return ResponseEntity.notFound().build();
    }
}
