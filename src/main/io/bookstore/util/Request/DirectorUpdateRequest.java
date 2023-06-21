package io.bookstore.util.Request;

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
public class DirectorUpdateRequest {
    private Long id;
    private String nameDirector;
    private String sonameDirector;
    private Integer ageDirector;
    private Date dateGetPositionDirector;

    public static Director fromDtoToDomain(DirectorUpdateRequest directorDto){
        return  Director.builder()
                .id(directorDto.getId())
                .nameDirector(directorDto.getNameDirector())
                .sonameDirector(directorDto.getSonameDirector())
                .ageDirector(directorDto.getAgeDirector())
                .dateGetPositionDirector(directorDto.getDateGetPositionDirector())
                .build();
    }
}
