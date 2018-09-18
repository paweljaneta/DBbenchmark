package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoTransaction;

import java.util.List;

@Repository
public interface MongoTransactionRepository extends MongoRepository<MongoTransaction, String> {
    List<MongoTransaction> findAllByStoreId(String storeId);
}
