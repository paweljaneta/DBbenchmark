package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.LastId;

public interface LastIndexRepository extends JpaRepository<LastId,Long> {
}
