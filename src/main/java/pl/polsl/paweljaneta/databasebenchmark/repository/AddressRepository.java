package pl.polsl.paweljaneta.databasebenchmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
