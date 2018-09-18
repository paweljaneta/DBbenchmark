package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.simpleScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoDiscount;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProductsInStores;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoDiscount;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProductsInStores;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlDiscount;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProductsInStores;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlStoreRepository;

@Component
public class AddProductToStoreScenarioMethods {
    @Autowired
    private SqlStoreRepository sqlStoreRepository;
    @Autowired
    private SqlProductsInStoresRepository sqlProductsInStoresRepository;
    @Autowired
    private SqlDiscountRepository sqlDiscountRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;

    @Autowired
    private MongoStoreRepository mongoStoreRepository;
    @Autowired
    private MongoProductsInStoresRepository mongoProductsInStoresRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;
    @Autowired
    private MongoProductRepository mongoProductRepository;

    @Autowired
    private NeoStoreRepository neoStoreRepository;
    @Autowired
    private NeoProductsInStoresRepository neoProductsInStoresRepository;
    @Autowired
    private NeoDiscountRepository neoDiscountRepository;
    @Autowired
    private NeoProductRepository neoProductRepository;


    @ExecTimeMeasure
    public SqlStore sqlGetStoreByEntityId(Long storeEntityId) {
        return sqlStoreRepository.findFirstByEntityId(storeEntityId);
    }

    @ExecTimeMeasure
    public SqlDiscount sqlGetDiscountByEntityId(Long entityId) {
        return sqlDiscountRepository.findByEntityId(entityId);
    }

    @ExecTimeMeasure
    public SqlProduct sqlCreateProduct(SqlDiscount discount) {
        SqlProduct result = new SqlProduct();
        result.setName("New Product");
        result.setPrice(8f);
        result.setDiscount(discount);
        sqlProductRepository.save(result);
        return result;
    }

    @ExecTimeMeasure
    public void sqlCreateProductInStore(SqlProduct product, SqlStore store, Long quantity) {
        SqlProductsInStores sqlProductsInStores = new SqlProductsInStores();
        sqlProductsInStores.setQuantity(quantity);
        sqlProductsInStores.setStoreId(store.getId());
        sqlProductsInStores.setProductId(product.getProductId());
        sqlProductsInStoresRepository.save(sqlProductsInStores);
    }


    @ExecTimeMeasure
    public MongoStore mongoGetStoreByEntityId(Long storeEntityId) {
        return mongoStoreRepository.findFirstByEntityId(storeEntityId);
    }

    @ExecTimeMeasure
    public MongoDiscount mongoGetDiscountByEntityId(Long entityId) {
        return mongoDiscountRepository.findByEntityId(entityId);
    }

    @ExecTimeMeasure
    public MongoProduct mongoCreateProduct(MongoDiscount discount) {
        MongoProduct result = new MongoProduct();
        result.setName("New Product");
        result.setPrice(8f);
        result.setDiscountId(discount.getId());
        mongoProductRepository.save(result);
        return result;
    }

    @ExecTimeMeasure
    public void mongoCreateProductInStore(MongoProduct product, MongoStore store, Long quantity) {
        MongoProductsInStores mongoProductsInStores = new MongoProductsInStores();
        mongoProductsInStores.setQuantity(quantity);
        mongoProductsInStores.setStoreId(store.getId());
        mongoProductsInStores.setProductId(product.getId());
        mongoProductsInStoresRepository.save(mongoProductsInStores);
    }


    @ExecTimeMeasure
    public NeoStore neoGetStoreByEntityId(Long storeEntityId) {
        return neoStoreRepository.findFirstByEntityId(storeEntityId);
    }

    @ExecTimeMeasure
    public NeoDiscount neoGetDiscountByEntityId(Long entityId) {
        return neoDiscountRepository.findByEntityId(entityId);
    }

    @ExecTimeMeasure
    public NeoProduct neoCreateProduct(NeoDiscount discount) {
        NeoProduct result = new NeoProduct();
        result.setName("New Product");
        result.setPrice(8f);
        result.setDiscount(discount);
        neoProductRepository.save(result);
        return result;
    }

    @ExecTimeMeasure
    public void neoCreateProductInStore(NeoProduct product, NeoStore store, Long quantity) {
        NeoProductsInStores neoProductsInStores = new NeoProductsInStores();
        neoProductsInStores.setQuantity(quantity);
        neoProductsInStores.setStore(store);
        neoProductsInStores.setProduct(product);
        neoProductsInStoresRepository.save(neoProductsInStores);
    }
}
