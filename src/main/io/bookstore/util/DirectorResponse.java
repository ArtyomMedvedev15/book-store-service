package io.bookstore.util;

import io.bookstore.domain.Director;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class DirectorResponse {
    private Long id;
    private String nameDirector;
    private String sonameDirector;
    private Integer ageDirector;
    private Date dateGetPositionDirector;

    public static DirectorResponse fromDomainToDto(Director directorDomain){
        return DirectorResponse.builder()
                .id(directorDomain.getId())
                .nameDirector(directorDomain.getNameDirector())
                .sonameDirector(directorDomain.getSonameDirector())
                .ageDirector(directorDomain.getAgeDirector())
                .dateGetPositionDirector(directorDomain.getDateGetPositionDirector())
                .build();
    }
}
