package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.model.Store;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class StoreService {
    private List<Store> stores;

    public StoreService() {
        stores = new ArrayList<>();

    }

    public void addStore(Store store) {
        stores.add(store);
    }


    public Store getStoreById(String code)
    {
        for(Store store:stores)
        {
            if(store.getCode().equalsIgnoreCase(code))
            {
                return store;
            }
        }
        return null;
    }


}
