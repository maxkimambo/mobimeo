package com.kimambo.mobimeo;

import com.kimambo.mobimeo.domain.Stop;
import com.kimambo.mobimeo.repository.StopRepository;
import com.kimambo.mobimeo.repository.StopRepositoryImpl;
import org.junit.*;

import java.util.List;

public class StopRepositoryTest {


    private StopRepository stopRepository;

    @Before
    public void init() {
        stopRepository = new StopRepositoryImpl();
    }

    @After
    public void tearDown() {
        stopRepository = null;
    }

    @Test
    public void ShouldReturnAllStops() {

        List<Stop> stops = stopRepository.getAll();

        int expected = 12;
        Assert.assertEquals(expected, stops.size());
    }

    @Test
    public void ShouldReturnSingleStopGivenCoordinates() {
        Stop result = stopRepository.getStopByCoordinates(1, 4);
        Integer expected = 1;
        Assert.assertEquals(expected, result.getId());
    }
}
