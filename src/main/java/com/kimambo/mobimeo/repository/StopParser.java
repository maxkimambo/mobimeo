package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Stop;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;

public class StopParser implements MobimeoParser<Stop> {
    @Override
    public Stop parse(String[] input) throws InvalidFormatException {

        try {
            int id = Integer.parseInt(input[0]);
            int x = Integer.parseInt(input[1]);
            int y = Integer.parseInt(input[2]);

            return new Stop(id, x,y);
        } catch (NumberFormatException e) {
           throw new InvalidFormatException(e.getMessage());
        }
    }
}
