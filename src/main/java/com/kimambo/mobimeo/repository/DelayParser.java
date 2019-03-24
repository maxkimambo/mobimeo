package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Delay;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;

public class DelayParser implements MobimeoParser<Delay> {
    @Override
    public Delay parse(String[] input) throws InvalidFormatException {

        if (input.length > 2){
            throw new InvalidFormatException("Unexpected input format");
        }

        String line = input[0];

        try{
            int delayMinutes = Integer.parseInt(input[1]);
            Delay delay = new Delay(line, delayMinutes);
            return delay;
        }catch(NumberFormatException nex){
            throw new InvalidFormatException(nex.getMessage());
        }
    }
}
