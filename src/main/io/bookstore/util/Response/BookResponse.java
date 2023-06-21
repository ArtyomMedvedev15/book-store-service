package io.bookstore.util.Response;

import io.bookstore.domain.Author;
import io.bookstore.domain.Book;
import io.bookstore.domain.Director;
import io.bookstore.domain.Store;
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
public class BookResponse {
    private Long idBook;
    private String nameBook;
    private String describeBook;
    private AuthorResponse authorResponse;
    private StoreResponse storeResponse;
    private Date dateStartSaleBook;

    public static BookResponse fromDomainToDto(Book bookDomain, Author authorDomain, List<Book>authorsBooks,
                                               Store storeDomain, Director directorDomain){
        return BookResponse.builder()
                .idBook(bookDomain.getIdBook())
                .nameBook(bookDomain.getNameBook())
                .describeBook(bookDomain.getDescribeBook())
                .authorResponse(AuthorResponse.fromDomainToDto(authorDomain,authorsBooks))
                .storeResponse(StoreResponse.fromDomainToDto(storeDomain,directorDomain))
                .build();
    }
}
