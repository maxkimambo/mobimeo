package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Stop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository {
    List<Stop> getAll();

    Stop getById(int id);

    Stop getStopByCoordinates(int x, int y);
}
