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
public class Director {
    private Long id;
    private String nameDirector;
    private String sonameDirector;
    private Integer ageDirector;
    private Date dateGetPositionDirector;


}
