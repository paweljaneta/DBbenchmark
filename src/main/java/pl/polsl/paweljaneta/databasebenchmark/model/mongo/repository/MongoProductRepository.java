package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;

import java.util.List;

@Repository
public interface MongoProductRepository extends MongoRepository<MongoProduct, String> {
    List<MongoProduct> findByIdIn(List<String> productIds);
}
