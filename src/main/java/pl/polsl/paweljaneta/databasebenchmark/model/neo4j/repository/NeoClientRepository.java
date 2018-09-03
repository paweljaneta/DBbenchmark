package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;

@Repository
public interface NeoClientRepository extends Neo4jRepository<NeoClient, Long> {
    NeoClient findByEntityId(Long entityId);
}
