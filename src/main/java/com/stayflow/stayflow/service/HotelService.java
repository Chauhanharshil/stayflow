package com.stayflow.stayflow.service;

import com.stayflow.stayflow.Exception.ResourceNotFoundException;
import com.stayflow.stayflow.dtos.HotelRequest;
import com.stayflow.stayflow.dtos.HotelResponse;
import com.stayflow.stayflow.dtos.RoomTypeRequest;
import com.stayflow.stayflow.entities.Hotel;
import com.stayflow.stayflow.entities.RoomType;
import com.stayflow.stayflow.repository.HotelRepository;
import com.stayflow.stayflow.repository.RoomTypeRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomTypeRepository roomTypeRepository;

    public HotelService (HotelRepository hotelRepository, RoomTypeRepository roomTypeRepository){
        this.hotelRepository = hotelRepository;
        this.roomTypeRepository = roomTypeRepository;
    }
    public HotelResponse createHotel(HotelRequest request) {
        Hotel hotel = Hotel.builder()
                .city(request.city())
                .name(request.name())
                .address(request.address())
                .starRating(request.starRating())
                .build();
        Hotel hotelnew = hotelRepository.save(hotel);
        return new HotelResponse(hotelnew.getName(), hotelnew.getCity(),hotelnew.getAddress(),hotelnew.getStarRating());
    }

    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelResponse> responses = new ArrayList<>();
        for(Hotel hotel : hotels){
            responses.add(new HotelResponse(hotel.getName(), hotel.getCity(), hotel.getAddress(), hotel.getStarRating()));
        }
        return responses;
    }

    public HotelResponse getHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No hotel with id "+ id));
        return new HotelResponse(hotel.getName(), hotel.getCity(), hotel.getAddress(), hotel.getStarRating());
    }

    public String createRoomType(Long id, RoomTypeRequest roomTypeRequest) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No hotel with id "+ id));

        RoomType roomType = RoomType.builder()
                .hotel(hotel)
                .name(roomTypeRequest.name())
                .capacity(roomTypeRequest.capacity())
                .pricePerNight(roomTypeRequest.pricePerNight())
                .build();

        roomTypeRepository.save(roomType);
        return "Success";


    }
}
