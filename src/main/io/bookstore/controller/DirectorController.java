package io.bookstore.controller;

import io.bookstore.domain.Director;
import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.util.Request.DirectorSaveRequest;
import io.bookstore.util.Response.DirectorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/director")
@Slf4j
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorServiceApi directorServiceApi;

    @GetMapping("/all")
    public ResponseEntity<?> getAllDirector() {
        var directorDtoList = directorServiceApi.getAll().stream().map(DirectorResponse::fromDomainToDto).toList();
        return ResponseEntity.ok().body(directorDtoList);
    }

    @GetMapping("/{idDirector}")
    public ResponseEntity<?> getDirectorById(@PathVariable("idDirector") Long idDirector) {
        var directorById = directorServiceApi.getById(idDirector);
        if (directorById != null) {
            return ResponseEntity.ok().body(DirectorResponse.fromDomainToDto(directorById));
        } else {
            return ResponseEntity.badRequest().body(String.format("Director with id %s not found", idDirector));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> getDirectorById(@RequestBody DirectorSaveRequest directorSaveRequest) {
        var directorSave = directorServiceApi.saveDirector(DirectorSaveRequest.fromDtoToDomain(directorSaveRequest));
        if(directorSave!=null){
            return ResponseEntity.ok().body(DirectorResponse.fromDomainToDto(directorSave));
        }else{
            return ResponseEntity.badRequest().body("Error on server side, try later");
        }
    }

    }
