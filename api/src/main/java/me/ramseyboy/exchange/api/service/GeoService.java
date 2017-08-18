package me.ramseyboy.exchange.api.service;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class GeoService {

    @PersistenceContext
    private EntityManager entityManager;

    public double computeDistance(double lon1, double lat1, double lon2, double lat2) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("compute_distance")
                .registerStoredProcedureParameter(0,
                        Double.class, ParameterMode.IN)
                .registerStoredProcedureParameter(1,
                        Double.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2,
                        Double.class, ParameterMode.IN)
                .registerStoredProcedureParameter(3,
                        Double.class, ParameterMode.IN)
                .setParameter(0, lon1)
                .setParameter(1, lat1)
                .setParameter(2, lon2)
                .setParameter(3, lat2);

        double distance = (double) query.getSingleResult();
        return distance * 0.000621371;
    }
}
