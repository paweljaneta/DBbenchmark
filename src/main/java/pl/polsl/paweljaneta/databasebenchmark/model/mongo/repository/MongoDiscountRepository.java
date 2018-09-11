package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoDiscount;

@Repository
public interface MongoDiscountRepository extends MongoRepository<MongoDiscount, String> {
    MongoDiscount findByEntityId(Long entityId);
}
