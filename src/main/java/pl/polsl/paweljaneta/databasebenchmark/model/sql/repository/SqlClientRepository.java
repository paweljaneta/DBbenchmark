package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;

import java.util.List;

@Repository
public interface SqlClientRepository extends JpaRepository<SqlClient, Long> {
    SqlClient findByEntityId(Long entityId);
}
