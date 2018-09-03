package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;

@Repository
public interface MongoClientRepository extends MongoRepository<MongoClient, String> {
    MongoClient findByEntityId(Long entityId);

}
