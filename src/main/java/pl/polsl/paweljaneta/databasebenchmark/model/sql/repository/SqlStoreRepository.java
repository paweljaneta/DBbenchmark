package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;

@Repository
public interface SqlStoreRepository extends JpaRepository<SqlStore, Long> {
}
