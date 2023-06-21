package io.bookstore.util.Request;

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
public class StoreSaveRequest {
    private String nameStore;
    private String cityStore;
    private String streetStore;
    private Long idDirectorStore;
    private Date dateOpenStore;

    public static Store fromDtoToDomain(StoreSaveRequest storeSaveRequest){
        return Store.builder()
                .nameStore(storeSaveRequest.getNameStore())
                .cityStore(storeSaveRequest.getCityStore())
                .streetStore(storeSaveRequest.getStreetStore())
                .idDirectorStore(storeSaveRequest.getIdDirectorStore())
                .dateOpenStore(storeSaveRequest.getDateOpenStore())
                .build();
    }
}
