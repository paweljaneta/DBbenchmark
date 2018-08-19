package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoAddress;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoCart;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlCart;

@Repository
public interface MongoCartRepository extends MongoRepository<MongoCart, String> {
}
