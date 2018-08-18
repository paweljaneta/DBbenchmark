package pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.Address;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {
}
