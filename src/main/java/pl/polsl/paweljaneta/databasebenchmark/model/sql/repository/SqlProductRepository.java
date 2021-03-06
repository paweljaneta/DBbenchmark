package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;

import java.util.List;

@Repository
public interface SqlProductRepository extends JpaRepository<SqlProduct, Long> {
    List<SqlProduct> findByProductIdIn(List<Long> productIds);

    List<SqlProduct> findAllByTransactionsIn(List<SqlTransaction> transactions);
}
