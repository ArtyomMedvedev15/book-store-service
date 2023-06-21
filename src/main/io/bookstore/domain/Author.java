package io.bookstore.domain;


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
public class Author {
    private Long idAuthor;
    private String nameAuthor;
    private String sonameAuthor;
    private String fanameAuthor;
    private Date dateAddToAuthor;
}
