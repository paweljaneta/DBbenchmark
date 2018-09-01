package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.*;

@Component
public class DatabaseCleaner {

    @Autowired
    private SqlAddressRepository sqladdressRepository;
    @Autowired
    private SqlClientRepository sqlclientRepository;
    @Autowired
    private SqlStoreRepository sqlstoreRepository;
    @Autowired
    private SqlDiscountRepository sqldiscountRepository;
    @Autowired
    private SqlProductRepository sqlproductRepository;
    @Autowired
    private SqlCartRepository sqlcartRepository;
    @Autowired
    private SqlOrderRepository sqlorderRepository;
    @Autowired
    private SqlShipmentRepository sqlshipmentRepository;
    @Autowired
    private SqlTransactionRepository sqltransactionRepository;
    @Autowired
    private SqlProductsInStoresRepository sqlproductsInStoresRepository;

    @Autowired
    private MongoAddressRepository mongoaddressRepository;
    @Autowired
    private MongoClientRepository mongoclientRepository;
    @Autowired
    private MongoStoreRepository mongostoreRepository;
    @Autowired
    private MongoDiscountRepository mongodiscountRepository;
    @Autowired
    private MongoProductRepository mongoproductRepository;
    @Autowired
    private MongoCartRepository mongocartRepository;
    @Autowired
    private MongoOrderRepository mongoorderRepository;
    @Autowired
    private MongoShipmentRepository mongoshipmentRepository;
    @Autowired
    private MongoTransactionRepository mongotransactionRepository;
    @Autowired
    private MongoProductsInStoresRepository mongoproductsInStoresRepository;

    @Autowired
    private NeoAddressRepository neoaddressRepository;
    @Autowired
    private NeoClientRepository neoclientRepository;
    @Autowired
    private NeoStoreRepository neostoreRepository;
    @Autowired
    private NeoDiscountRepository neodiscountRepository;
    @Autowired
    private NeoProductRepository neoproductRepository;
    @Autowired
    private NeoCartRepository neocartRepository;
    @Autowired
    private NeoOrderRepository neoorderRepository;
    @Autowired
    private NeoShipmentRepository neoshipmentRepository;
    @Autowired
    private NeoTransactionRepository neotransactionRepository;
    @Autowired
    private NeoProductsInStoresRepository neoproductsInStoresRepository;


    public void cleanSql() {
        sqladdressRepository.deleteAll();
        sqlclientRepository.deleteAll();
        sqlstoreRepository.deleteAll();
        sqldiscountRepository.deleteAll();
        sqlproductRepository.deleteAll();
        sqlcartRepository.deleteAll();
        sqlorderRepository.deleteAll();
        sqlshipmentRepository.deleteAll();
        sqltransactionRepository.deleteAll();
        sqlproductsInStoresRepository.deleteAll();
    }

    public void cleanMongo() {
        mongoaddressRepository.deleteAll();
        mongoclientRepository.deleteAll();
        mongostoreRepository.deleteAll();
        mongodiscountRepository.deleteAll();
        mongoproductRepository.deleteAll();
        mongocartRepository.deleteAll();
        mongoorderRepository.deleteAll();
        mongoshipmentRepository.deleteAll();
        mongotransactionRepository.deleteAll();
        mongoproductsInStoresRepository.deleteAll();
    }

    public void cleanNeo() {
        neoaddressRepository.deleteAll();
        neoclientRepository.deleteAll();
        neostoreRepository.deleteAll();
        neodiscountRepository.deleteAll();
        neoproductRepository.deleteAll();
        neocartRepository.deleteAll();
        neoorderRepository.deleteAll();
        neoshipmentRepository.deleteAll();
        neotransactionRepository.deleteAll();
        neoproductsInStoresRepository.deleteAll();
    }

    public void cleanAll() {
        cleanSql();
        cleanMongo();
        cleanNeo();
    }
}
