package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.repository.LinesRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LinesRepositoryImpl implements LinesRepository {

    private List<Line> lines;

    public LinesRepositoryImpl() {
        lines = new ArrayList<>();
        lines.add(new Line(0, "M4"));
        lines.add(new Line(1, "200"));
        lines.add(new Line(2, "S75"));
    }

    @Override
    public List<Line> getAll() {
        return lines;
    }

    @Override
    public Line getById(int id) {
        Line line = lines.stream().filter(l -> l.getId().equals(id))
                .findAny().orElse(null);

        return line;
    }

    @Override
    public Line getByName(String name) {
        Line line = lines.stream().filter(l -> l.getName().equals(name))
                .findAny().orElse(null);

        return line;
    }
}
