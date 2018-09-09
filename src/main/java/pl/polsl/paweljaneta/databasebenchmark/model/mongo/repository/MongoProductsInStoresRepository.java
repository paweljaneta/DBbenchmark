package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProductsInStores;

import java.util.List;

@Repository
public interface MongoProductsInStoresRepository extends MongoRepository<MongoProductsInStores, String> {
    List<MongoProductsInStores> findAllByStoreId(String storeId);

    MongoProductsInStores findFirstByProductIdAndStoreId(String productId, String storeId);
}
