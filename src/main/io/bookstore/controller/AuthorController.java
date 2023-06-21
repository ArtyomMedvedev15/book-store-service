package io.bookstore.controller;

import io.bookstore.service.api.AuthorServiceApi;
import io.bookstore.util.Response.AuthorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
@Slf4j
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceApi authorServiceApi;

    @GetMapping("/all")
    public ResponseEntity<?>getAllAuthors(){
        var autorsList = authorServiceApi.getAll().stream()
                .map(o1-> AuthorResponse.fromDomainToDto(o1,authorServiceApi.getAllAuthorBook(o1.getIdAuthor()))).collect(Collectors.toList());
        return ResponseEntity.ok().body(autorsList);
    }

}
