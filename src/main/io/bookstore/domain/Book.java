package io.bookstore.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Book {
    private Long idBook;
    private String nameBook;
    private String describeBook;
    private Long idAuthorBook;
    private Long idStoreBook;
    private Date dateStartSaleBook;
}
