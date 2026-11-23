package com.stayflow.stayflow.controller;

import com.stayflow.stayflow.dtos.HotelRequest;
import com.stayflow.stayflow.dtos.HotelResponse;
import com.stayflow.stayflow.dtos.RoomTypeRequest;
import com.stayflow.stayflow.service.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody HotelRequest request){
        System.out.println("Hello");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelService.createHotel(request));

    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponse> getHotel(@PathVariable Long id){
        return ResponseEntity.ok(hotelService.getHotel(id));
    }

    @PostMapping("/{id}/room-types")
    public ResponseEntity<String> addRoomType(@PathVariable Long id, @RequestBody RoomTypeRequest roomTypeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelService.createRoomType(id, roomTypeRequest));
    }

}
