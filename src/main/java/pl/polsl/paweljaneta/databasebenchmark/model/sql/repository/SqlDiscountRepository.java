package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlDiscount;

@Repository
public interface SqlDiscountRepository  extends JpaRepository<SqlDiscount, Long> {
    SqlDiscount findByEntityId(Long entityId);
}
