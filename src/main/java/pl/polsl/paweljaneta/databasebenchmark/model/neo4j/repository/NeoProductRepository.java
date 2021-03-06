package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;

import java.util.List;

@Repository
public interface NeoProductRepository extends Neo4jRepository<NeoProduct, Long> {
    List<NeoProduct> findByIdIn(List<Long> productIds);
}
