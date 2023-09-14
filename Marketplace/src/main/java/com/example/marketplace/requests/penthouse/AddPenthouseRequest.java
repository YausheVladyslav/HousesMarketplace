package com.example.marketplace.requests.penthouse;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddPenthouseRequest {

    @NotBlank
    private String announcementName;
    @NotNull
    @Min(value = 100000)
    private long price;
    // to do enum
    private String houseType;
    private String country;
    private String city;
    private String street;
    @NotNull
    private int size;
    @NotNull
    private int room;
    @NotNull
    private int bedroom;
    @NotNull
    private int bathroom;
    // to do enum
    private String furnishing;
    @Min(1800)
    @Max(3000)
    @NotNull
    private int yearOfConstruction;
    private boolean hasTerrace;
    private boolean hasBar;
    private boolean hasBalcony;
    private boolean hasPool;
    private boolean hasHotTub;
}
