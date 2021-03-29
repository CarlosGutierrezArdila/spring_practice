package com.calculators.demo.controllers;

import com.calculators.demo.dto.HouseDTO;
import com.calculators.demo.dto.RoomDTO;
import com.calculators.demo.services.HouseAreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/area")
public class AreaController {

    @PostMapping("/fullarea")
    public ResponseEntity getFullArea(@RequestBody HouseDTO house){
        return new ResponseEntity(HouseAreaService.calculateFullArea(house), HttpStatus.OK);
    }

    @PostMapping("/roomarea")
    public ResponseEntity getRoomArea(@RequestBody HouseDTO house){
        return new ResponseEntity(HouseAreaService.calculateRoomArea(house), HttpStatus.OK);
    }

    @PostMapping("/calculateValue")
    public ResponseEntity calculateValue(@RequestBody HouseDTO house){
        return new ResponseEntity(HouseAreaService.calculateValue(house), HttpStatus.OK);
    }
    @PostMapping("/biggestRoom")
    public ResponseEntity biggestRoom(@RequestBody HouseDTO house){
        return new ResponseEntity(HouseAreaService.biggestRoom(house), HttpStatus.OK);
    }

    @GetMapping("/fullarea")
    public ResponseEntity getFullArea(){
        List<RoomDTO> rooms = new ArrayList<>();
        rooms.add(new RoomDTO("baño",4.0, 4.0));
        rooms.add(new RoomDTO("sala",5.0, 5.0));
        return new ResponseEntity(new HouseDTO("test", "callefalsa", rooms), HttpStatus.OK);
    }


}
