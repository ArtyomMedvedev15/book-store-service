package io.bookstore.util.Request;

import io.bookstore.domain.Author;
import io.bookstore.domain.Book;
import io.bookstore.util.Response.AuthorResponse;
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
public class AuthorSaveRequest {
    private String nameAuthor;
    private String sonameAuthor;
    private String fanameAuthor;
    private Date dateAddToAuthor;

    public static Author fromDtoToDomain(AuthorSaveRequest AuthorSaveRequest) {
        return Author.builder()
                .nameAuthor(AuthorSaveRequest.getNameAuthor())
                .sonameAuthor(AuthorSaveRequest.getSonameAuthor())
                .fanameAuthor(AuthorSaveRequest.getFanameAuthor())
                .dateAddToAuthor(AuthorSaveRequest.getDateAddToAuthor())
                .build();
    }
}
