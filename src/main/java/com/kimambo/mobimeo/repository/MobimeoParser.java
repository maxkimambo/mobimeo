package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.exceptions.InvalidFormatException;

public interface MobimeoParser<T> {
    T parse(String [] input) throws InvalidFormatException;
}
