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
public class StoreUpdateRequest {
    private Long idStore;
    private String nameStore;
    private String cityStore;
    private String streetStore;
    private Long idDirectorStore;
    private Date dateOpenStore;

    public static Store fromDtoToDomain(StoreUpdateRequest storeUpdateRequest){
        return Store.builder()
                .idStore(storeUpdateRequest.getIdStore())
                .nameStore(storeUpdateRequest.getNameStore())
                .cityStore(storeUpdateRequest.getCityStore())
                .streetStore(storeUpdateRequest.getStreetStore())
                .idDirectorStore(storeUpdateRequest.getIdDirectorStore())
                .dateOpenStore(storeUpdateRequest.getDateOpenStore())
                .build();
    }
}
