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
public class Store {
    private Long idStore;
    private String nameStore;
    private String cityStore;
    private String streetStore;
    private Long idDirectorStore;
    private Date dateOpenStore;
}
