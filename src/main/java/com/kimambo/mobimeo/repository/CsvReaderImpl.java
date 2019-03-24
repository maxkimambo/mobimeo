package com.kimambo.mobimeo.repository;

import com.kimambo.mobimeo.exceptions.InvalidFormatException;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderImpl<T> implements CsvReader<T> {

    private String filePath;
    private MobimeoParser parser;

    private List<T> values;
    public CsvReaderImpl(String filePath, MobimeoParser parser){
        this.filePath = filePath;
        this.parser = parser;
        this.values = new ArrayList<>();
    }

    private void read() throws IOException, InvalidFormatException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        CSVReader csvReader = new CSVReader(reader);
        String[] columns;
        int skipCount = 0;
        while ((columns = csvReader.readNext()) != null) {
            if (skipCount == 0) {
                skipCount++;
            } else {
                T parsedResult = (T) parser.parse(columns);
                this.values.add(parsedResult);
            }
        }
    }


    @Override
    public List<T> getValues() throws IOException, InvalidFormatException {

        if(this.values.isEmpty()){
            read();
        }
        return values;
    }

}
