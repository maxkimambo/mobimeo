package com.kimambo.mobimeo;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.repository.LinesRepository;
import com.kimambo.mobimeo.repository.LinesRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LinesRepositoryTest {

    private LinesRepository linesRepository;

    @Before
    public void init() {
        linesRepository = new LinesRepositoryImpl();
    }

    @Test
    public void ShouldReturnAllLines() {
        List<Line> result = linesRepository.getAll();
        int expected = 3;
        Assert.assertEquals(expected, result.size());
    }

    @Test
    public void ShouldReturnLineByName() {
        Line result = linesRepository.getByName("M4");
        String expected = "M4";
        Assert.assertEquals(expected, result.getName());
    }

    @Test
    public void ShouldReturnLineById() {
        Line result = linesRepository.getById(1);
        String expected = "200";
        Assert.assertEquals(expected, result.getName());
    }
}
