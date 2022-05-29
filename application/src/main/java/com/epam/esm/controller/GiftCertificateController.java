package com.epam.esm.controller;

import com.epam.esm.dto.BaseResponse;
import com.epam.esm.dto.reponse.GiftCertificateGetResponse;
import com.epam.esm.dto.request.GiftCertificatePostRequest;
import com.epam.esm.exception.NoDataFoundException;
import com.epam.esm.service.gift_certificate.GiftCertificateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/gift_certificate")
@AllArgsConstructor
public class GiftCertificateController {

    private final GiftCertificateService giftCertificateService;

    @PostMapping(value = "/create")
    public ResponseEntity<BaseResponse<GiftCertificateGetResponse>> create(
            @RequestBody GiftCertificatePostRequest createCertificate
            ){
        GiftCertificateGetResponse response = giftCertificateService.create(createCertificate);
        response.add(linkTo(methodOn(OrderController.class)
                .getOrdersForCertificate(response.getId(), 100, 0))
                .withRel("orders"));
        return ResponseEntity.status(201)
                .body(new BaseResponse<>(201, "certificate created", response));
    }

    @GetMapping(value = "/get")
    @ResponseBody
    public ResponseEntity<BaseResponse<GiftCertificateGetResponse>> get(
            @RequestParam Long id
    ){
        GiftCertificateGetResponse response = giftCertificateService.get(id);
        response.add(linkTo(methodOn(OrderController.class)
                        .getOrdersForCertificate(response.getId(), 100, 0))
                .withRel("orders"));
        return ResponseEntity.ok(new BaseResponse(200, "gift certificate", response));
    }

    @GetMapping(value = "/get_all")
    public ResponseEntity<BaseResponse<List<GiftCertificateGetResponse>>> getAll(
            @RequestParam(required = false, name = "search_word", defaultValue = "") String searchWord,
            @RequestParam(required = false, name = "tag_name") String tagName,
            @RequestParam(required = false, name = "do_name_sort") boolean doNameSort,
            @RequestParam(required = false, name = "do_date_sort") boolean doDateSort,
            @RequestParam(required = false, name = "is_descending") boolean isDescending,
            @RequestParam(required = false, name = "limit", defaultValue = "10") int limit,
            @RequestParam(required = false, name = "offset", defaultValue = "0") int offset

    ){
        List<GiftCertificateGetResponse> certificates = giftCertificateService.getAll(
                searchWord, tagName, doNameSort, doDateSort, isDescending, limit, offset
        );
        certificates.forEach(certificate -> {
            certificate.add(linkTo(methodOn(OrderController.class)
                    .getOrdersForCertificate(certificate.getId(), 100, 0))
                    .withRel("orders"));
        });
        return ResponseEntity.ok(new BaseResponse<>(200,"certificate list", certificates));
    }

    @GetMapping(value = "/get/tags")
    public ResponseEntity<BaseResponse<List<GiftCertificateGetResponse>>> getWithMultipleTags(
            @RequestParam(value = "tag") List<String> tags,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset
    ){
        List<GiftCertificateGetResponse> certificates
                = giftCertificateService.searchWithMultipleTags(tags, limit, offset);
        certificates.forEach(certificate -> {
            certificate.add(linkTo(methodOn(OrderController.class)
                    .getOrdersForCertificate(certificate.getId(), 100, 0))
                    .withRel("orders"));
        });
        return ResponseEntity.ok(new BaseResponse<>(200,"certificate list", certificates));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<BaseResponse> delete(
            @RequestParam Long id
    ){
        int delete = giftCertificateService.delete(id);
        if(delete == 1)
            return ResponseEntity.ok(new BaseResponse(204, "certificate deleted", null));
        throw new NoDataFoundException("no certificate to delete with id: " + id);
    }

    @PatchMapping(value = "/update")
    public ResponseEntity<BaseResponse<GiftCertificateGetResponse>> update(
            @RequestBody GiftCertificatePostRequest update,
            @RequestParam(value = "id") Long certificateId
    ){
        GiftCertificateGetResponse response = giftCertificateService.update(update, certificateId);
        response.add(linkTo(methodOn(OrderController.class)
                .getOrdersForCertificate(response.getId(), 100, 0))
                .withRel("orders"));
        return ResponseEntity.ok(new BaseResponse<>(200, "certificate updated", response));
    }

    @PatchMapping(value = "/update/duration")
    public ResponseEntity<BaseResponse<GiftCertificateGetResponse>> updateDuration(
            @RequestParam Long id,
            @RequestParam int duration
    ){
        GiftCertificateGetResponse response = giftCertificateService.updateDuration(duration, id);
        response.add(linkTo(methodOn(OrderController.class)
                .getOrdersForCertificate(response.getId(), 100, 0))
                .withRel("orders"));
        return ResponseEntity.ok(new BaseResponse<>(200, "duration updated", response));
    }
}