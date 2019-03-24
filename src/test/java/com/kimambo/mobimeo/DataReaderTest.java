package com.kimambo.mobimeo;

import com.kimambo.mobimeo.domain.Delay;
import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.domain.Stop;
import com.kimambo.mobimeo.exceptions.InvalidFormatException;
import com.kimambo.mobimeo.repository.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class DataReaderTest {

    @Test
    public void ShouldReadDelaysGivenFilePath(){

        DelayParser delayParser = new DelayParser();
        CsvReader<Delay> delayReader = new CsvReaderImpl<>("./data/delays.csv", delayParser);

        try{
            List<Delay> delays = delayReader.getValues();
            int expectedSize = 3;

            Assert.assertEquals(expectedSize, delays.size());

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ShouldReadLinesGivenFilePath(){
        LineParser lineParser = new LineParser();
        CsvReader<Line> lineReader = new CsvReaderImpl<>("./data/lines.csv", lineParser);
        try{
            List<Line> lines = lineReader.getValues();
            int expectedLines = 3;

            Assert.assertEquals(expectedLines, lines.size());
        }catch(InvalidFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    @Test
    public void ShouldReadStopsGivenFilePath(){
        StopParser stopParser = new StopParser();
        CsvReader<Stop> stopReader = new CsvReaderImpl<>("./data/stops.csv", stopParser);
         try {
             List<Stop> stops = stopReader.getValues();
             int expectedStops = 12;
             Assert.assertEquals(expectedStops, stops.size());
         } catch (InvalidFormatException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
