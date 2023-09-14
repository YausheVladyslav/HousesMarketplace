package com.example.marketplace.services;

import com.example.marketplace.entities.UserEntity;
import com.example.marketplace.entities.penthouse.PenthouseEntity;
import com.example.marketplace.repositories.PenthouseRepository;
import com.example.marketplace.responses.PenthouseResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PenthouseService {

    private final PenthouseRepository penthouseRepository;
    private final EntityManager entityManager;

    private static final int PAGE_SIZE = 2;

    public void savePenthouse(UserEntity user,
                              String announcementName,
                              long price,
                              String penthouseType,
                              String country,
                              String city,
                              String street,
                              int size,
                              int room,
                              int bedroom,
                              int bathroom,
                              String furnishing,
                              int yearOfConstruction,
                              boolean terrace,
                              boolean bar,
                              boolean balcony,
                              boolean pool,
                              boolean hotTub
    ) {
        PenthouseEntity penthouseEntity = new PenthouseEntity();
        penthouseEntity.setTitle(announcementName);
        penthouseEntity.setPrice(price);
        penthouseEntity.setPenthouseType(penthouseType);
        penthouseEntity.setCountry(country);
        penthouseEntity.setCity(city);
        penthouseEntity.setStreet(street);
        penthouseEntity.setSize(size);
        penthouseEntity.setRoom(room);
        penthouseEntity.setBedroom(bedroom);
        penthouseEntity.setBathroom(bathroom);
        penthouseEntity.setFurnishing(furnishing);
        penthouseEntity.setYearOfConstruction(yearOfConstruction);
        penthouseEntity.setHasTerrace(terrace);
        penthouseEntity.setHasBar(bar);
        penthouseEntity.setHasBalcony(balcony);
        penthouseEntity.setHasPool(pool);
        penthouseEntity.setHasHotTub(hotTub);
        penthouseEntity.setUser(user);
        penthouseRepository.save(penthouseEntity);
    }


    private Page<PenthouseResponse> fromPenthouseEntityToResponseInPage(Page<PenthouseEntity> penthouses) {
        return penthouses.map(penthouse -> new PenthouseResponse(
                penthouse.getUser().getFirstName(),
                penthouse.getUser().getSecondName(),
                penthouse.getUser().getUsername(),
                penthouse.getTitle(),
                penthouse.getPrice(),
                penthouse.getPenthouseType(),
                penthouse.getCountry(),
                penthouse.getCity(),
                penthouse.getStreet(),
                penthouse.getSize(),
                penthouse.getRoom(),
                penthouse.getBedroom(),
                penthouse.getBathroom(),
                penthouse.getFurnishing(),
                penthouse.getYearOfConstruction(),
                penthouse.isHasTerrace(),
                penthouse.isHasBar(),
                penthouse.isHasBalcony(),
                penthouse.isHasPool(),
                penthouse.isHasHotTub()
        ));
    }

    public Page<PenthouseResponse> getAllPenthouses(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        return fromPenthouseEntityToResponseInPage(penthouseRepository.findAll(pageable));
    }

    public Page<PenthouseResponse> getAllByTitle(int pageNumber, String name) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        return fromPenthouseEntityToResponseInPage(
                penthouseRepository.findByTitleContainingIgnoreCase(pageable, name));
    }

    public Page<PenthouseResponse> getPenthousesByPriceDesc(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("price").descending());
        return fromPenthouseEntityToResponseInPage(penthouseRepository.findAll(pageable));
    }

    public Page<PenthouseResponse> getPenthousesByPriceAsc(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("price").ascending());
        return fromPenthouseEntityToResponseInPage(penthouseRepository.findAll(pageable));
    }

    public Page<PenthouseResponse> getPenthousesByCreatedAsc(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("createdOn").ascending());
        return fromPenthouseEntityToResponseInPage(penthouseRepository.findAll(pageable));
    }

    public Page<PenthouseResponse> getPenthousesByCreatedDesc(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("createdOn").descending());
        return fromPenthouseEntityToResponseInPage(penthouseRepository.findAll(pageable));
    }

    public List<PenthouseResponse> findPenthousesByParams(
            int pageNumber,
            boolean terrace,
            boolean bar,
            boolean balcony,
            boolean pool,
            boolean hotTub
    ) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PenthouseEntity> query = builder.createQuery(PenthouseEntity.class);
        Root<PenthouseEntity> penthouseRoot = query.from(PenthouseEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (terrace) {
            predicates.add(builder.equal(penthouseRoot.get("hasTerrace"), true));
        }
        if (bar) {
            predicates.add(builder.equal(penthouseRoot.get("hasBar"), true));
        }
        if (balcony) {
            predicates.add(builder.equal(penthouseRoot.get("hasBalcony"), true));
        }
        if (pool) {
            predicates.add(builder.equal(penthouseRoot.get("hasPool"), true));
        }
        if (hotTub) {
            predicates.add(builder.equal(penthouseRoot.get("hasHotTub"), true));
        }
        query.where(predicates.toArray(new Predicate[0]));

        List<PenthouseEntity> list = entityManager.createQuery(query).getResultList();

        PagedListHolder<PenthouseResponse> page =
                new PagedListHolder<>(fromPenthouseEntityToResponseInPage(new PageImpl<>(list)).toList());
        page.setPageSize(PAGE_SIZE);
        page.setPage(pageNumber);
        return page.getPageList();
    }
}