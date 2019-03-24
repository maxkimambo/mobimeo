package com.kimambo.mobimeo.controllers;

import com.kimambo.mobimeo.domain.Line;
import com.kimambo.mobimeo.domain.ScheduleItem;
import com.kimambo.mobimeo.exceptions.ScheduleNotFoundException;
import com.kimambo.mobimeo.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api")
@Validated
public class VehiclesController {

    private ScheduleService scheduleService;

    @Autowired
    public VehiclesController(ScheduleService scheduleService) {

        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/vehicles/{x}/{y}/{time}", method = GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<ScheduleItem> getVehicleAtStopAndTime(@PathVariable("x") @NotNull @Min(0) int x,
                                                                @PathVariable("y") @NotNull @Min(0) int y,
                                                                @PathVariable @NotNull String time)
                                                                throws ScheduleNotFoundException {

        try {
            LocalTime requestTime = LocalTime.parse(time);
            ScheduleItem scheduleItem = scheduleService.getVehicleForGivenTimeAndStop(x, y, requestTime);
            return ResponseEntity.ok().body(scheduleItem);

        } catch (Exception e) {
            throw new ScheduleNotFoundException("Invalid parameters passed, cannot retrieve given schedule");
        }
    }

    @RequestMapping(value = "/vehicles/next/{x}/{y}/{time}", method = GET, produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<List<ScheduleItem>> getNextVehicle(@PathVariable("x") @NotNull @Min(0) int x,
                                                       @PathVariable("y") @NotNull @Min(0) int y,
                                                       @PathVariable @NotNull String time)
            throws ScheduleNotFoundException {

        try {
            LocalTime requestTime = LocalTime.parse(time);
            List<ScheduleItem> schedule = scheduleService.getNextVehicleForGivenStop(x, y, requestTime);
            return ResponseEntity.ok().body(schedule);

        } catch (Exception e) {
            throw new ScheduleNotFoundException("Invalid parameters passed, cannot retrieve given schedule");
        }

    }

}
