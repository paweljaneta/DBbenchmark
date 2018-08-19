package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;

@Repository
public interface SqlProductRepository extends JpaRepository<SqlProduct, Long> {
}
