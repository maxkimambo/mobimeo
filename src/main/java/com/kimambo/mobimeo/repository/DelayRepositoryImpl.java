package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Delay;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DelayRepositoryImpl implements DelayRepository {

    private List<Delay> delays;
    private CsvReader<Delay> delayReader;


    public DelayRepositoryImpl(){

        try {
            delays = new ArrayList<>();
            DelayParser delayParser = new DelayParser();
            CsvReader<Delay> delayReader = new CsvReaderImpl<>("./data/delays.csv", delayParser);
            delays = delayReader.getValues();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Delay> getDelays(){

        return delays;
    }

    @Override
    public int getDelayByLineName(String name){
        Delay delay = delays.stream()
                .filter(d -> d.getName().equals(name))
                .findAny()
                .orElse(null);
        if (delay == null){
            return 0;
        }
        return delay.getMinutes();
    }
}
