package io.bookstore.controller;

import io.bookstore.domain.Author;
import io.bookstore.service.api.AuthorServiceApi;
import io.bookstore.util.Request.AuthorSaveRequest;
import io.bookstore.util.Response.AuthorResponse;
import io.bookstore.util.Response.DirectorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/author")
@Slf4j
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceApi authorServiceApi;

    @GetMapping("/all")
    public ResponseEntity<?> getAllAuthors() {
        var authorList = authorServiceApi.getAll().stream()
                .map(o1 -> AuthorResponse.fromDomainToDto(o1, authorServiceApi.getAllAuthorBook(o1.getIdAuthor()))).collect(Collectors.toList());
        return ResponseEntity.ok().body(authorList);
    }

    @GetMapping("/{idAuthor}")
    public ResponseEntity<?> getAuthorById(@PathVariable("idAuthor") Long idAuthor) {
        var authorById = authorServiceApi.getById(idAuthor);
        if (authorById != null) {
            log.info("Get author with id {} with endpoint in {}", idAuthor, new Date());
            return ResponseEntity.ok().body(AuthorResponse.fromDomainToDto(authorById,authorServiceApi.getAllAuthorBook(authorById.getIdAuthor())));
        } else {
            log.error("Author with id {} not found with endpoint in {}", idAuthor, new Date());
            return ResponseEntity.badRequest().body(String.format("Author with id %s not found", idAuthor));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?>saveAuthor(@RequestBody AuthorSaveRequest authorSaveRequest){
        var author_save_result = authorServiceApi.saveAuthor(AuthorSaveRequest.fromDtoToDomain(authorSaveRequest));

        if(author_save_result!=null){
            log.info("Save new author with name {} with endpoint in {}",
                    String.format("%s %s",authorSaveRequest.getSonameAuthor(),authorSaveRequest.getNameAuthor()),new Date());
            return ResponseEntity.ok().body(AuthorResponse.fromDomainToDto(author_save_result,null));
        }else{
            log.error("Cannot save new author, check connection to db in {}",new Date());
            return ResponseEntity.badRequest().body("Error on server side, try later");
         }

    }

}
