package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.*;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StopRepositoryImpl implements StopRepository {

    private List<Stop> stops;

    public StopRepositoryImpl() {
        try {
            stops = new ArrayList<>();
            StopParser stopParser = new StopParser();
            CsvReader<Stop> stopReader = new CsvReaderImpl<>("./data/stops.csv", stopParser);
            stops = stopReader.getValues();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

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
