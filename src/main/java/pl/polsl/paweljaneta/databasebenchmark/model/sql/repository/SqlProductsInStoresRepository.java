package pl.polsl.paweljaneta.databasebenchmark.model.sql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProductsInStores;

import java.util.List;

@Repository
public interface SqlProductsInStoresRepository extends JpaRepository<SqlProductsInStores, Long> {

    List<SqlProductsInStores> findAllByStoreId(Long storeId);

    SqlProductsInStores findFirstByProductIdAndStoreId(Long productId, Long storeId);
}
