package io.bookstore.util.Response;

import io.bookstore.domain.Author;
import io.bookstore.domain.Book;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class AuthorResponse {
    private Long idAuthor;
    private String nameAuthor;
    private String sonameAuthor;
    private String fanameAuthor;
    private List<Book> booksAuthor;
    private Date dateAddToAuthor;

    public static AuthorResponse fromDomainToDto(Author authorDomain,List<Book>booksAuthor){
        return AuthorResponse.builder()
                .idAuthor(authorDomain.getIdAuthor())
                .nameAuthor(authorDomain.getNameAuthor())
                .sonameAuthor(authorDomain.getSonameAuthor())
                .fanameAuthor(authorDomain.getFanameAuthor())
                .booksAuthor(booksAuthor)
                .dateAddToAuthor(authorDomain.getDateAddToAuthor())
                .build();
    }

}
