package io.bookstore.util.Response;

import io.bookstore.domain.Director;
import io.bookstore.domain.Store;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class StoreResponse {
    private Long idStore;
    private String nameStore;
    private String cityStore;
    private String streetStore;
    private DirectorResponse directorStore;
    private Date dateOpenStore;

    public static StoreResponse fromDomainToDto(Store storeDomain, Director directorDomain){
        return StoreResponse.builder()
                .idStore(storeDomain.getIdStore())
                .nameStore(storeDomain.getNameStore())
                .cityStore(storeDomain.getCityStore())
                .streetStore(storeDomain.getStreetStore())
                .directorStore(DirectorResponse.fromDomainToDto(directorDomain))
                .dateOpenStore(storeDomain.getDateOpenStore())
                .build();
    }
}
