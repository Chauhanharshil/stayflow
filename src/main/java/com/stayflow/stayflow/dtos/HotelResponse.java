package com.stayflow.stayflow.dtos;

public record HotelResponse(
        String name,
        String city,
        String address,
        int starRating
) {
}
