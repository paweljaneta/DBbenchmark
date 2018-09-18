package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;

import java.util.List;

@Repository
public interface SqlTransactionRepository extends JpaRepository<SqlTransaction, Long> {
    List<SqlTransaction> findAllByStoreId(Long storeId);
}
