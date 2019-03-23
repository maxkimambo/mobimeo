package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Line;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LinesRepository {
    List<Line> getAll();

    Line getById(int id);

    Line getByName(String name);
}
