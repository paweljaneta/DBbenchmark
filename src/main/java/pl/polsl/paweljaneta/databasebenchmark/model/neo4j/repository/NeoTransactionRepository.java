package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoTransaction;

import java.util.List;

@Repository
public interface NeoTransactionRepository extends Neo4jRepository<NeoTransaction, Long> {

    @Query("MATCH (d:discount)<-[rd:HAS]-(p:product)<-[rp:PRODUCTS]-(t:transaction)-[r:HAS]->(s:store) WHERE ID(s)={storeId} RETURN d,rd,p,rp,t,r,s")
    List<NeoTransaction> findAllByStoreId(@Param("storeId") Long storeId);

    @Query("MATCH (d:discount)<-[rd:HAS]-(p:product)<-[rp:PRODUCTS]-(t:transaction)-[r:HAS]->(c:client),(t)-[rs:HAS]->(s:store) WHERE ID(c)={clientId} RETURN d,rd,p,rp,t,r,c,rs,s")
    List<NeoTransaction> findAllByClientId(@Param("clientId") Long clientId);

    @Query("MATCH (d:discount)<-[rd:HAS]-(p:product)<-[rp:PRODUCTS]-(t:transaction) WHERE t.deliveryMode = {dm} RETURN d,rd,p,rp,t")
    List<NeoTransaction> findAllByDeliveryMode(@Param("dm") DeliveryMode deliveryMode);
}
