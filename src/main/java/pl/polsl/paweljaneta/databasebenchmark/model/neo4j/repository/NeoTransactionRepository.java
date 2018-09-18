package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoTransaction;

import java.util.List;

@Repository
public interface NeoTransactionRepository extends Neo4jRepository<NeoTransaction, Long> {
    @Query("MATCH (disc:discount)<-[rd:HAS]-(prod:product)<-[r:HAS]-(trans:transaction)-[:HAS]->(store) WHERE ID(store) = {storeId} RETURN trans,r,prod,rd,disc")
    List<NeoTransaction> findAllByStoreId(@Param("storeId") Long storeId);
}
