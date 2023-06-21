package io.bookstore.controller;

import io.bookstore.service.api.AuthorServiceApi;
import io.bookstore.service.api.BookServiceApi;
import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.service.api.StoreServiceApi;
import io.bookstore.util.Response.BookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/book")
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private final BookServiceApi bookServiceApi;

    private final AuthorServiceApi authorServiceApi;

    private final DirectorServiceApi directorServiceApi;

    private final StoreServiceApi storeServiceApi;

    @GetMapping("/all")
    public ResponseEntity<?>getAllBooks(){
        var bookList = bookServiceApi.getAll().stream().map(o1 -> BookResponse.fromDomainToDto(o1,
                authorServiceApi.getById(o1.getIdAuthorBook()),authorServiceApi.getAllAuthorBook(o1.getIdAuthorBook()),
                storeServiceApi.getById(o1.getIdStoreBook()),directorServiceApi.getById(storeServiceApi.getById(o1.getIdStoreBook()).getIdDirectorStore())))
                .toList();
        log.info("Get all books with endpoint in {}",new Date());
        return ResponseEntity.ok().body(bookList);
    }

}
