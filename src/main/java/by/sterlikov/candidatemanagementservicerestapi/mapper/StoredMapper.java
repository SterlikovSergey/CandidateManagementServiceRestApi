package by.sterlikov.candidatemanagementservicerestapi.mapper;

import by.sterlikov.candidatemanagementservicerestapi.dto.StoreDto;
import by.sterlikov.candidatemanagementservicerestapi.model.Store;
import org.springframework.stereotype.Component;
@Component
public class StoredMapper {
    public Store storeDtoToStore(StoreDto dto){
        Store store = new Store();
        store.setName(dto.getName());
        return store;
    }
}
