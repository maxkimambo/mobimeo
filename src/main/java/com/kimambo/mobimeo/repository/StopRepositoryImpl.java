package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StopRepositoryImpl implements StopRepository {

    private List<Stop> stops;

    public StopRepositoryImpl() {
        stops = new ArrayList<>();

        stops.add(new Stop(0, 1, 1));
        stops.add(new Stop(1, 1, 4));
        stops.add(new Stop(2, 1, 7));
        stops.add(new Stop(3, 2, 9));
        stops.add(new Stop(4, 3, 11));
        stops.add(new Stop(5, 3, 1));
        stops.add(new Stop(6, 3, 4));
        stops.add(new Stop(7, 3, 7));
        stops.add(new Stop(8, 1, 10));
        stops.add(new Stop(9, 2, 12));
        stops.add(new Stop(10, 4, 9));
        stops.add(new Stop(11, 5, 7));

    }

    @Override
    public List<Stop> getAll() {
        return stops;
    }


    @Override
    public Stop getById(int id) {

        Stop result = this.stops.stream()
                .filter(s -> s.getId().equals(id))
                .findAny()
                .orElse(null);
        return result;
    }


    @Override
    public Stop getStopByCoordinates(int x, int y) {

        Stop result = this.stops.stream()
                .filter(s -> s.equals(x, y))
                .findAny()
                .orElse(null);

        return result;
    }
}
