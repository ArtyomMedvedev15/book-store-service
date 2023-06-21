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
public class BookSaveRequest {
    private String nameBook;
    private String describeBook;
    private Long idAuthorBook;
    private Long idStoreBook;
    private Date dateStartSaleBook;

    public static Book fromDtoToDomain(BookSaveRequest bookSaveRequest){
        return Book.builder()
                .nameBook(bookSaveRequest.getNameBook())
                .describeBook(bookSaveRequest.getDescribeBook())
                .idAuthorBook(bookSaveRequest.getIdAuthorBook())
                .idStoreBook(bookSaveRequest.getIdStoreBook())
                .dateStartSaleBook(bookSaveRequest.getDateStartSaleBook())
                .build();
    }
}
