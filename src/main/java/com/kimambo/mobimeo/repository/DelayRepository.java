package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.domain.Delay;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DelayRepository {
    List<Delay> getDelays();
    int getDelayByLineName(String name);
}
