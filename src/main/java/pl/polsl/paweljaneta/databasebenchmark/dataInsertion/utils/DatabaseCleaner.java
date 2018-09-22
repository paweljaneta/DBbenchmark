package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DatabaseCleaner {
    Logger logger = Logger.getLogger(this.getClass().getName());

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
        logger.log(Level.INFO, "cleanSql");
        logger.log(Level.INFO, "sqlshipmentRepository clean start");

        sqlshipmentRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqlshipmentRepository clean finish");
        logger.log(Level.INFO, "sqlorderRepository clean start");

        sqlorderRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqlorderRepository clean finish");
        logger.log(Level.INFO, "sqlcartRepository clean start");

        sqlcartRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqlcartRepository clean finish");
        logger.log(Level.INFO, "sqltransactionRepository clean start");

        sqltransactionRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqltransactionRepository clean finish");
        logger.log(Level.INFO, "sqlproductsInStoresRepository clean start");

        sqlproductsInStoresRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqlproductsInStoresRepository clean finish");
        logger.log(Level.INFO, "sqlproductRepository clean start");

        sqlproductRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqlproductRepository clean finish");
        logger.log(Level.INFO, "sqldiscountRepository clean start");

        sqldiscountRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqldiscountRepository clean finish");
        logger.log(Level.INFO, "sqlclientRepository clean start");

        sqlclientRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqlclientRepository clean finish");
        logger.log(Level.INFO, "sqlstoreRepository clean start");

        sqlstoreRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqlstoreRepository clean finish");
        logger.log(Level.INFO, "sqladdressRepository clean start");

        sqladdressRepository.deleteAllInBatch();

        logger.log(Level.INFO, "sqladdressRepository clean finish");
        logger.log(Level.INFO, "sql clean finish");
    }

    public void cleanMongo() {
        logger.log(Level.INFO, "cleanMongo");
        logger.log(Level.INFO, "mongoaddressRepository clean start");

        mongoaddressRepository.deleteAll();

        logger.log(Level.INFO, "mongoaddressRepository clean finish");
        logger.log(Level.INFO, "mongoclientRepository clean start");

        mongoclientRepository.deleteAll();

        logger.log(Level.INFO, "mongoclientRepository clean finish");
        logger.log(Level.INFO, "mongostoreRepository clean start");

        mongostoreRepository.deleteAll();

        logger.log(Level.INFO, "mongostoreRepository clean finish");
        logger.log(Level.INFO, "mongodiscountRepository clean start");

        mongodiscountRepository.deleteAll();

        logger.log(Level.INFO, "mongodiscountRepository clean finish");
        logger.log(Level.INFO, "mongoproductRepository clean start");

        mongoproductRepository.deleteAll();

        logger.log(Level.INFO, "mongoproductRepository clean finish");
        logger.log(Level.INFO, "mongocartRepository clean start");

        mongocartRepository.deleteAll();

        logger.log(Level.INFO, "mongocartRepository clean finish");
        logger.log(Level.INFO, "mongoorderRepository clean start");

        mongoorderRepository.deleteAll();

        logger.log(Level.INFO, "mongoorderRepository clean finish");
        logger.log(Level.INFO, "mongoshipmentRepository clean start");

        mongoshipmentRepository.deleteAll();

        logger.log(Level.INFO, "mongoshipmentRepository clean finish");
        logger.log(Level.INFO, "mongotransactionRepository clean start");

        mongotransactionRepository.deleteAll();

        logger.log(Level.INFO, "mongotransactionRepository clean finish");
        logger.log(Level.INFO, "mongoproductsInStoresRepository clean start");

        mongoproductsInStoresRepository.deleteAll();

        logger.log(Level.INFO, "mongoproductsInStoresRepository clean finish");
        logger.log(Level.INFO, "mogno clean finish");
    }

    public void cleanNeo() {
        logger.log(Level.INFO, "cleanNeo");
        logger.log(Level.INFO, "neoaddressRepository clean start");

        neoaddressRepository.deleteAll();

        logger.log(Level.INFO, "neoaddressRepository clean finish");
        logger.log(Level.INFO, "neoclientRepository clean start");

        neoclientRepository.deleteAll();

        logger.log(Level.INFO, "neoclientRepository clean finish");
        logger.log(Level.INFO, "neostoreRepository clean start");

        neostoreRepository.deleteAll();

        logger.log(Level.INFO, "neostoreRepository clean finish");
        logger.log(Level.INFO, "neodiscountRepository clean start");

        neodiscountRepository.deleteAll();

        logger.log(Level.INFO, "neodiscountRepository clean finish");
        logger.log(Level.INFO, "neoproductRepository clean start");

        neoproductRepository.deleteAll();

        logger.log(Level.INFO, "neoproductRepository clean finish");
        logger.log(Level.INFO, "neocartRepository clean start");

        neocartRepository.deleteAll();

        logger.log(Level.INFO, "neocartRepository clean finish");
        logger.log(Level.INFO, "neoorderRepository clean start");

        neoorderRepository.deleteAll();

        logger.log(Level.INFO, "neoorderRepository clean finish");
        logger.log(Level.INFO, "neoshipmentRepository clean start");

        neoshipmentRepository.deleteAll();

        logger.log(Level.INFO, "neoshipmentRepository clean finish");
        logger.log(Level.INFO, "neotransactionRepository clean start");

        neotransactionRepository.deleteAll();

        logger.log(Level.INFO, "neotransactionRepository clean finish");
        logger.log(Level.INFO, "neoproductsInStoresRepository clean start");

        neoproductsInStoresRepository.deleteAll();

        logger.log(Level.INFO, "neoproductsInStoresRepository clean finish");
        logger.log(Level.INFO, "neo clean finish");
    }

    public void cleanAll() {
        cleanSql();
        cleanMongo();
        cleanNeo();
    }
}
