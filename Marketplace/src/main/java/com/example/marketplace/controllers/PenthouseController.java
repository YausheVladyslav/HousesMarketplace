package com.example.marketplace.controllers;

import com.example.marketplace.entities.UserEntity;
import com.example.marketplace.entities.penthouse.OrderByPenthouseEnum;
import com.example.marketplace.requests.penthouse.AddPenthouseRequest;
import com.example.marketplace.requests.penthouse.PenthouseByTitleRequest;
import com.example.marketplace.requests.penthouse.PenthouseFilterRequest;
import com.example.marketplace.responses.PenthouseResponse;
import com.example.marketplace.services.PenthouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("penthouse")
public class PenthouseController {

    private final PenthouseService penthouseService;

    @PostMapping("/save-penthouse")
    public ResponseEntity<Void> addPenthouse(
            @RequestBody AddPenthouseRequest request,
            @AuthenticationPrincipal UserEntity user) {
        penthouseService.savePenthouse(
                user,
                request.getAnnouncementName(),
                request.getPrice(),
                request.getHouseType(),
                request.getCountry(),
                request.getCity(),
                request.getStreet(),
                request.getSize(),
                request.getRoom(),
                request.getBedroom(),
                request.getBathroom(),
                request.getFurnishing(),
                request.getYearOfConstruction(),
                request.isHasTerrace(),
                request.isHasBar(),
                request.isHasBalcony(),
                request.isHasPool(),
                request.isHasHotTub());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-penthouses-filter")
    public ResponseEntity<List<PenthouseResponse>> getPenthousesByFilter(
            @RequestBody PenthouseFilterRequest request,
            @RequestParam("page") int page) {
        return ResponseEntity.ok(penthouseService.findPenthousesByParams(
                page,
                request.isTerrace(),
                request.isBar(),
                request.isBalcony(),
                request.isPool(),
                request.isHotTub()
        ));
    }

    @GetMapping("/penthouses-by-name")
    public ResponseEntity<Page<PenthouseResponse>> getPenthousesByFilter(
            @Valid @RequestBody PenthouseByTitleRequest request,
            @RequestParam("page") int page) {
        return ResponseEntity.ok(penthouseService.getAllByTitle(page, request.getTitle()));
    }

    @GetMapping("/penthouses")
    public ResponseEntity<Page<PenthouseResponse>> penthousesByParam(
            @RequestParam("sort") String sort,
            @RequestParam("page") int page
    ) {
        if (sort.equalsIgnoreCase(String.valueOf(OrderByPenthouseEnum.EXPENSIVE))) {
            return ResponseEntity.ok(penthouseService.getPenthousesByPriceDesc(page));
        }
        if (sort.equalsIgnoreCase(String.valueOf(OrderByPenthouseEnum.CHEAP))) {
            return ResponseEntity.ok(penthouseService.getPenthousesByPriceAsc(page));
        }
        if (sort.equalsIgnoreCase(String.valueOf(OrderByPenthouseEnum.NEWER))) {
            return ResponseEntity.ok(penthouseService.getPenthousesByCreatedDesc(page));
        }
        if (sort.equalsIgnoreCase(String.valueOf(OrderByPenthouseEnum.OLDER))) {
            return ResponseEntity.ok(penthouseService.getPenthousesByCreatedAsc(page));
        }
        return ResponseEntity.ok(penthouseService.getAllPenthouses(page));
    }

}
