package io.bookstore.controller;

import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.service.api.StoreServiceApi;
import io.bookstore.util.Response.StoreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        log.info("Get all store with endpoint in {}", new Date());
        return ResponseEntity.ok().body(storeList);
    }

    @GetMapping("/{idStore}")
    public ResponseEntity<?>getStoreById(@PathVariable("idStore")Long idStore){
        var storeById = storeServiceApi.getById(idStore);
        if(storeById!=null){
            log.info("Get store with id {} with endpoint in {}",idStore,new Date());
            return ResponseEntity.ok().body(StoreResponse.fromDomainToDto(storeById,directorServiceApi.getById(storeById.getIdDirectorStore())));
        }else{
            log.error("Store with id {} not found in {}",idStore,new Date());
            return ResponseEntity.badRequest().body(String.format("Store with id %s not found",idStore));
        }
    }
}
