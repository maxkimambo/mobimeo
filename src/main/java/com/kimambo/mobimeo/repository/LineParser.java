package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;

public class LineParser implements MobimeoParser<Line> {

    @Override
    public Line parse(String[] input) throws InvalidFormatException {

        try {
            int id = Integer.parseInt(input[0]);
            String lineName = input[1];
            Line line = new Line(id, lineName);
            return line;
        }catch(NumberFormatException nfe){
            throw new InvalidFormatException("Unexpected format");
        }
    }
}
