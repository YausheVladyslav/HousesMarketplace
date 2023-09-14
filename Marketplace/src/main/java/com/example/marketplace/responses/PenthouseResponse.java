package com.example.marketplace.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PenthouseResponse {

    private String userFirstName;
    private String userSecondName;
    private String userEmail;
    private String announcementName;
    private long price;
    private String penthouseType;
    private String country;
    private String city;
    private String street;
    private int size;
    private int room;
    private int bedroom;
    private int bathroom;
    private String furnishing;
    private int yearOfConstruction;
    private boolean hasTerrace;
    private boolean hasBar;
    private boolean hasBalcony;
    private boolean hasPool;
    private boolean hasHotTub;

}
