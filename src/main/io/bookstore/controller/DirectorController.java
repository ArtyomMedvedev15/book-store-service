package io.bookstore.controller;

import io.bookstore.domain.Director;
import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.util.DirectorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

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


}
