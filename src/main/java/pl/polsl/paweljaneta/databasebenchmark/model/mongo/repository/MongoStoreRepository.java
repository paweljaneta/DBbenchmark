package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;

@Repository
public interface MongoStoreRepository extends MongoRepository<MongoStore, String> {
    MongoStore findFirstByEntityId(Long entityId);
}
