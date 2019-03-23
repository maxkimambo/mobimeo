package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Delay;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DelayRepositoryImpl implements DelayRepository {

    private List<Delay> delays;

    public DelayRepositoryImpl(){
        delays = new ArrayList<>();

        delays.add(new Delay("M4", 1));
        delays.add(new Delay("200", 2));
        delays.add(new Delay("S75", 10));
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
