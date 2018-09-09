package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProductsInStores;

import java.util.List;

@Repository
public interface NeoProductsInStoresRepository extends Neo4jRepository<NeoProductsInStores, Long> {

    @Query("MATCH (disc:discount)<-[rd:HAS]-(prod:product)<-[r:HAS]-(prodInStores:productsInStores)-[:HAS]->(store) WHERE ID(store) = {storeId} RETURN prodInStores,r,prod,rd,disc")
    List<NeoProductsInStores> findByStoreId(@Param("storeId") Long storeId);

    @Query("MATCH (disc:discount)<-[rd:HAS]-(prod:product)<-[r:HAS]-(prodInStores:productsInStores)-[:HAS]->(store) WHERE ID(store) = {storeId} AND ID(product) = {productId} RETURN prodInStores,r,prod,rd,disc")
    NeoProductsInStores findFirstByProductIdAndStoreId(@Param("productId") Long productId, @Param("storeId") Long storeId);
}
