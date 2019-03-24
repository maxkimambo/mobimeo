package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LinesRepositoryImpl implements LinesRepository {

    private List<Line> lines;

    public LinesRepositoryImpl() {

        lines = new ArrayList<>();
        try {
            LineParser lineParser = new LineParser();
            CsvReader<Line> linesReader = new CsvReaderImpl<>("./data/lines.csv", lineParser);
            lines = linesReader.getValues();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
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
