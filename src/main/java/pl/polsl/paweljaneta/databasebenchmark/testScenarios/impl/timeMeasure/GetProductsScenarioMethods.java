package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.dto.AddressDTO;
import pl.polsl.paweljaneta.databasebenchmark.dto.DiscountDTO;
import pl.polsl.paweljaneta.databasebenchmark.dto.ProductDTO;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlStoreRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetProductsScenarioMethods {

    private SqlStoreRepository sqlStoreRepository;

    private MongoStoreRepository mongoStoreRepository;

    private NeoStoreRepository neoStoreRepository;

    private SqlProductsInStoresRepository sqlProductsInStoresRepository;

    private MongoProductsInStoresRepository mongoProductsInStoresRepository;

    private NeoProductsInStoresRepository neoProductsInStoresRepository;

    private SqlProductRepository sqlProductRepository;

    private MongoProductRepository mongoProductRepository;

    @Autowired
    public GetProductsScenarioMethods(SqlStoreRepository sqlStoreRepository,
                                      MongoStoreRepository mongoStoreRepository, NeoStoreRepository neoStoreRepository,
                                      SqlProductsInStoresRepository sqlProductsInStoresRepository,
                                      MongoProductsInStoresRepository mongoProductsInStoresRepository,
                                      NeoProductsInStoresRepository neoProductsInStoresRepository,
                                      SqlProductRepository sqlProductRepository, MongoProductRepository mongoProductRepository) {
        this.sqlStoreRepository = sqlStoreRepository;
        this.mongoStoreRepository = mongoStoreRepository;
        this.neoStoreRepository = neoStoreRepository;
        this.sqlProductsInStoresRepository = sqlProductsInStoresRepository;
        this.mongoProductsInStoresRepository = mongoProductsInStoresRepository;
        this.neoProductsInStoresRepository = neoProductsInStoresRepository;
        this.sqlProductRepository = sqlProductRepository;
        this.mongoProductRepository = mongoProductRepository;
    }

    @ExecTimeMeasure
    public SqlStore getSqlStoreByEntityId(Long id) {
        return sqlStoreRepository.findFirstByEntityId(id);
    }

    @ExecTimeMeasure
    public MongoStore getMongoStoreByEntityId(Long id) {
        return mongoStoreRepository.findFirstByEntityId(id);
    }

    @ExecTimeMeasure
    public NeoStore getNeoStoreByEntityId(Long id) {
        return neoStoreRepository.findFirstByEntityId(id);
    }

    @ExecTimeMeasure
    public AddressDTO getSqlAddressFromStore(SqlStore store) {
        SqlAddress address = store.getAddress();
        return new AddressDTO(address.getCity(), address.getPostalCode(), address.getStreet(), address.getStreetNumber(), address.getEntityId());
    }

    @ExecTimeMeasure
    public AddressDTO getMongoAddressFromStore(MongoStore store) {
        MongoAddress address = store.getAddress();
        return new AddressDTO(address.getCity(), address.getPostalCode(), address.getStreet(), address.getStreetNumber(), address.getEntityId());
    }

    @ExecTimeMeasure
    public AddressDTO getNeoAddressFromStore(NeoStore store) {
        NeoAddress address = store.getAddress();
        return new AddressDTO(address.getCity(), address.getPostalCode(), address.getStreet(), address.getStreetNumber(), address.getEntityId());
    }

    @ExecTimeMeasure
    public List<ProductDTO> getSqlProductsFromStore(SqlStore store) {
        List<ProductDTO> result = new ArrayList<>();
        List<SqlProductsInStores> allByStoreId = sqlProductsInStoresRepository.findAllByStoreId(store.getId());
        for (SqlProductsInStores sqlProductsInStores : allByStoreId) {
            result.add(mapSqlProductToDTO(sqlProductRepository.findById(sqlProductsInStores.getProductId()).get()));
        }
        return result;
    }

    private ProductDTO mapSqlProductToDTO(SqlProduct product) {
        return new ProductDTO(product.getName(), product.getPrice(), mapSqlDiscountToDTO(product.getDiscount()), product.getEntityId());
    }

    private DiscountDTO mapSqlDiscountToDTO(SqlDiscount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountValue(discount.getDiscountValue());
        discountDTO.setEntityId(discount.getEntityId());
        return discountDTO;
    }

    @ExecTimeMeasure
    public List<ProductDTO> getMongoProductsFromStore(MongoStore store) {
        List<ProductDTO> result = new ArrayList<>();
        List<MongoProductsInStores> allByStoreId = mongoProductsInStoresRepository.findAllByStoreId(store.getId());
        for (MongoProductsInStores mongoProductsInStores : allByStoreId) {
            result.add(mapMongoProductToDTO(mongoProductRepository.findById(mongoProductsInStores.getProductId()).get()));
        }
        return result;
    }

    private ProductDTO mapMongoProductToDTO(MongoProduct product) {
        return new ProductDTO(product.getName(), product.getPrice(), mapMongoDiscountToDTO(product.getDiscount()), product.getEntityId());
    }

    private DiscountDTO mapMongoDiscountToDTO(MongoDiscount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountValue(discount.getDiscountValue());
        discountDTO.setEntityId(discount.getEntityId());
        return discountDTO;
    }

    @ExecTimeMeasure
    public List<ProductDTO> getNeoProductsFromStore(NeoStore store) {
        List<ProductDTO> result = new ArrayList<>();
        Iterable<NeoProductsInStores> allByStoreId = neoProductsInStoresRepository.findByStoreId(store.getId());
        for (NeoProductsInStores neoProductsInStores : allByStoreId) {
            result.add(mapNeoProductToDTO(neoProductsInStores.getProduct()));
        }
        return result;
    }

    private ProductDTO mapNeoProductToDTO(NeoProduct product) {
        return new ProductDTO(product.getName(), product.getPrice(), mapNeoDiscountToDTO(product.getDiscount()), product.getEntityId());
    }

    private DiscountDTO mapNeoDiscountToDTO(NeoDiscount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountValue(discount.getDiscountValue());
        discountDTO.setEntityId(discount.getEntityId());
        return discountDTO;
    }
}
