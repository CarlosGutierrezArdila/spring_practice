package com.calculators.demo.services;

import com.calculators.demo.dto.*;

import java.util.ArrayList;
import java.util.List;

public class HouseAreaService {

    public static FullAreaDTO calculateFullArea(HouseDTO house) {
        Double area = 0.0;
        for (RoomDTO room : house.getHabitaciones()) {
            area += room.calculateArea();
        }
        return new FullAreaDTO(house.getNombre(), "El area total de la casa es: " + area + " m2");

    }

    public static List<RoomAreaDTO> calculateRoomArea(HouseDTO house) {
        List<RoomAreaDTO> response = new ArrayList<>();
        for (RoomDTO room : house.getHabitaciones()) {
            response.add(new RoomAreaDTO(room.getNombre(), room.calculateArea()));
        }
        return response;
    }

    public static MessageDTO calculateValue(HouseDTO house) {
        Double area = 0.0;
        for (RoomDTO room : house.getHabitaciones()) {
            area += room.calculateArea();
        }
        return new MessageDTO("el valor de la casa es: " + area * 800 + "USD");
    }

    public static RoomDTO biggestRoom(HouseDTO house) {
        RoomDTO biggestRoom = null;
        if (house.getHabitaciones() != null) {
            if(house.getHabitaciones().size()>0) biggestRoom = house.getHabitaciones().get(0);
            for (RoomDTO room : house.getHabitaciones()) {
                if (room.calculateArea() > biggestRoom.calculateArea()) {
                    biggestRoom = room;
                }
            }
        }
        return biggestRoom;
    }
}
