package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.List;

public interface CsvReader<T> {
    List<T> getValues() throws IOException, InvalidFormatException;
}
