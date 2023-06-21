package io.bookstore.controller;

import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.service.api.StoreServiceApi;
import io.bookstore.util.Response.StoreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/store")
@Slf4j
@RequiredArgsConstructor
public class StoreController {

    private final StoreServiceApi storeServiceApi;

    private final DirectorServiceApi directorServiceApi;

    @GetMapping("/all")
    public ResponseEntity<?>getAllStores(){
        var storeList = storeServiceApi.getAll().stream()
                .map(o1 -> StoreResponse.fromDomainToDto(o1, directorServiceApi.getById(o1.getIdDirectorStore()))).toList();
        return ResponseEntity.ok().body(storeList);
    }
}
