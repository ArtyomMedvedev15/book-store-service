package io.bookstore.util.Request;

import io.bookstore.domain.Director;
import io.bookstore.util.Response.DirectorResponse;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class DirectorSaveRequest {
    private String nameDirector;
    private String sonameDirector;
    private Integer ageDirector;
    private Date dateGetPositionDirector;

    public static Director fromDtoToDomain(DirectorSaveRequest directorDto){
        return  Director.builder()
                .nameDirector(directorDto.getNameDirector())
                .sonameDirector(directorDto.getSonameDirector())
                .ageDirector(directorDto.getAgeDirector())
                .dateGetPositionDirector(directorDto.getDateGetPositionDirector())
                .build();
    }
}
