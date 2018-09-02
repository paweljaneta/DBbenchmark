package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;

@Repository
public interface NeoStoreRepository extends Neo4jRepository<NeoStore, Long> {
    NeoStore findFirstByEntityId(Long entityId);
}
