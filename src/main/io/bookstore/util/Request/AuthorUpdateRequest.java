package io.bookstore.util.Request;

import io.bookstore.domain.Author;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class AuthorUpdateRequest {
    private Long idAuthor;
    private String nameAuthor;
    private String sonameAuthor;
    private String fanameAuthor;
    private Date dateAddToAuthor;

    public static Author fromDtoToDomain(AuthorUpdateRequest authorUpdateRequest) {
        return Author.builder()
                .idAuthor(authorUpdateRequest.getIdAuthor())
                .nameAuthor(authorUpdateRequest.getNameAuthor())
                .sonameAuthor(authorUpdateRequest.getSonameAuthor())
                .fanameAuthor(authorUpdateRequest.getFanameAuthor())
                .dateAddToAuthor(authorUpdateRequest.getDateAddToAuthor())
                .build();
    }
}
