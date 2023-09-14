package com.example.marketplace.entities.penthouse;

import com.example.marketplace.entities.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "penthouse")
public class PenthouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String title;
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
    @CreationTimestamp
    private LocalDate createdOn;
    private boolean hasTerrace;
    private boolean hasBar;
    private boolean hasBalcony;
    private boolean hasPool;
    private boolean hasHotTub;
}
