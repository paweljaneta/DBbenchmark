package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.dto.AddressDTO;
import pl.polsl.paweljaneta.databasebenchmark.dto.DiscountDTO;
import pl.polsl.paweljaneta.databasebenchmark.dto.ProductDTO;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlShipmentRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GetProductsScenario extends BaseScenario {
    @Autowired
    private DataConfig dataConfig;
    @Autowired
    private SqlStoreRepository sqlStoreRepository;
    @Autowired
    private MongoStoreRepository mongoStoreRepository;
    @Autowired
    private NeoStoreRepository neoStoreRepository;
    @Autowired
    private SqlProductsInStoresRepository sqlProductsInStoresRepository;
    @Autowired
    private MongoProductsInStoresRepository mongoProductsInStoresRepository;
    @Autowired
    private NeoProductsInStoresRepository neoProductsInStoresRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;
    @Autowired
    private MongoProductRepository mongoProductRepository;
    @Autowired
    private NeoProductRepository neoProductRepository;

    @Override
    protected void before() {

    }

    @Override
    @ExecTimeMeasure
    protected void execute() {

    }

    @Override
    protected void after() {

    }

    private Long getStoreEntityId(){
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }

    @ExecTimeMeasure
    private SqlStore getSqlStoreByEntityId(Long id){
        return sqlStoreRepository.findFirstByEntityId(id);
    }

    @ExecTimeMeasure
    private MongoStore getMongoStoreByEntityId(Long id){
        return mongoStoreRepository.findFirstByEntityId(id);
    }

    @ExecTimeMeasure
    private NeoStore getNeoStoreByEntityId(Long id){
        return neoStoreRepository.findFirstByEntityId(id);
    }
    
    @ExecTimeMeasure
    private AddressDTO getSqlAddressFromStore(SqlStore store){
        SqlAddress address = store.getAddress();
        return new AddressDTO(address.getCity(), address.getPostalCode(), address.getStreet(), address.getStreetNumber(), address.getEntityId());
    }

    @ExecTimeMeasure
    private AddressDTO getMongoAddressFromStore(MongoStore store){
        MongoAddress address = store.getAddress();
        return new AddressDTO(address.getCity(), address.getPostalCode(), address.getStreet(), address.getStreetNumber(), address.getEntityId());
    }
    
    @ExecTimeMeasure
    private AddressDTO getNeoAddressFromStore(NeoStore store){
        NeoAddress address = store.getAddress();
        return new AddressDTO(address.getCity(), address.getPostalCode(), address.getStreet(), address.getStreetNumber(), address.getEntityId());
    }
    
    @ExecTimeMeasure
    private List<ProductDTO> getSqlProductsFromStore(SqlStore store){
        List<ProductDTO> result = new ArrayList<>();
        List<SqlProductsInStores> allByStoreId = sqlProductsInStoresRepository.findAllByStoreId(store.getId());
        for (SqlProductsInStores sqlProductsInStores : allByStoreId) {
            result.add(mapSqlProductToDTO(sqlProductRepository.getOne(sqlProductsInStores.getProductId())));
        }
        return result;
    }
    private ProductDTO mapSqlProductToDTO(SqlProduct product){
        return new ProductDTO(product.getName(), product.getPrice(), mapSqlDiscountToDTO(product.getDiscount()), product.getEntityId());
    }
    private DiscountDTO mapSqlDiscountToDTO(SqlDiscount discount){
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountValue(discount.getDiscountValue());
        discountDTO.setEntityId(discount.getEntityId());
        return discountDTO;
    }

    @ExecTimeMeasure
    private List<ProductDTO> getMongoProductsFromStore(MongoStore store){
        List<ProductDTO> result = new ArrayList<>();
        List<MongoProductsInStores> allByStoreId = mongoProductsInStoresRepository.findAllByStore(store);
        for (MongoProductsInStores mongoProductsInStores : allByStoreId) {
            result.add(mapMongoProductToDTO(mongoProductsInStores.getProduct()));
        }
        return result;
    }
    private ProductDTO mapMongoProductToDTO(MongoProduct product){
        return new ProductDTO(product.getName(), product.getPrice(), mapMongoDiscountToDTO(product.getDiscount()), product.getEntityId());
    }
    private DiscountDTO mapMongoDiscountToDTO(MongoDiscount discount){
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountValue(discount.getDiscountValue());
        discountDTO.setEntityId(discount.getEntityId());
        return discountDTO;
    }

    @ExecTimeMeasure
    private List<ProductDTO> getNeoProductsFromStore(NeoStore store){
        List<ProductDTO> result = new ArrayList<>();
        List<NeoProductsInStores> allByStoreId = neoProductsInStoresRepository.findAllByStore(store);
        for (NeoProductsInStores neoProductsInStores : allByStoreId) {
            result.add(mapNeoProductToDTO(neoProductsInStores.getProduct()));
        }
        return result;
    }
    private ProductDTO mapNeoProductToDTO(NeoProduct product){
        return new ProductDTO(product.getName(), product.getPrice(), mapNeoDiscountToDTO(product.getDiscount()), product.getEntityId());
    }
    private DiscountDTO mapNeoDiscountToDTO(NeoDiscount discount){
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setDiscountValue(discount.getDiscountValue());
        discountDTO.setEntityId(discount.getEntityId());
        return discountDTO;
    }
}
