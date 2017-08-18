package me.ramseyboy.exchange.api.controller;

import me.ramseyboy.exchange.api.controller.model.DistanceResponseGeom;
import me.ramseyboy.exchange.api.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoController {

    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/distance", params = {"lon1", "lat1", "lon2", "lat2"})
    public ResponseEntity<DistanceResponseGeom> switchDistance(@RequestParam("lon1") Double lon1,
                                                               @RequestParam("lat1") Double lat1,
                                                               @RequestParam("lon2") Double lon2,
                                                               @RequestParam("lat2") Double lat2) {
        if (lon1 == null || lat1 == null || lon2 == null || lat2 == null) {
            return ResponseEntity.badRequest()
                    .build();
        }

        double distance = geoService.computeDistance(lon1, lat1, lon2, lat2);

        DistanceResponseGeom res = new DistanceResponseGeom(distance);
        return ResponseEntity.ok(res);
    }
}
