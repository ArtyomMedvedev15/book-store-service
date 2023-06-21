package io.bookstore.util.Request;

import io.bookstore.domain.Book;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class BookUpdateRequest {
    private Long idBook;
    private String nameBook;
    private String describeBook;
    private Long idAuthorBook;
    private Long idStoreBook;
    private Date dateStartSaleBook;

    public static Book fromDtoToDomain(BookUpdateRequest bookUpdateRequest){
        return Book.builder()
                .idBook(bookUpdateRequest.getIdBook())
                .nameBook(bookUpdateRequest.getNameBook())
                .describeBook(bookUpdateRequest.getDescribeBook())
                .idAuthorBook(bookUpdateRequest.getIdAuthorBook())
                .idStoreBook(bookUpdateRequest.getIdStoreBook())
                .dateStartSaleBook(bookUpdateRequest.getDateStartSaleBook())
                .build();
    }
}
