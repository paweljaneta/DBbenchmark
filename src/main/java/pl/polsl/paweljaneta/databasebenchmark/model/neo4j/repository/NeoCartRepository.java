package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoCart;

@Repository
public interface NeoCartRepository extends Neo4jRepository<NeoCart, Long> {
}
