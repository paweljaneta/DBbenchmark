package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProductsInStores;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProductsInStores;

@Repository
public interface MongoProductsInStoresRepository extends MongoRepository<MongoProductsInStores, String> {
}