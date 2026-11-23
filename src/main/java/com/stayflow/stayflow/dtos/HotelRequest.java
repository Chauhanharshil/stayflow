package com.stayflow.stayflow.dtos;

public record HotelRequest(
        String name,
        String city,
        String address,
        int starRating
) {
}
