package com.stayflow.stayflow.dtos;

import jakarta.validation.constraints.NotNull;

public record RoomTypeRequest (
        @NotNull
        String name,

        @NotNull
        int capacity,

        @NotNull
        Double pricePerNight
){
}
