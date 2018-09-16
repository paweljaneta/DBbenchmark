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
        System.out.println("cleanSql");
        System.out.println("sqlshipmentRepository clean start");

        sqlshipmentRepository.deleteAllInBatch();

        System.out.println("sqlshipmentRepository clean finish");
        System.out.println("sqlorderRepository clean start");

        sqlorderRepository.deleteAllInBatch();

        System.out.println("sqlorderRepository clean finish");
        System.out.println("sqlcartRepository clean start");

        sqlcartRepository.deleteAllInBatch();

        System.out.println("sqlcartRepository clean finish");
        System.out.println("sqltransactionRepository clean start");

        sqltransactionRepository.deleteAllInBatch();

        System.out.println("sqltransactionRepository clean finish");
        System.out.println("sqlproductsInStoresRepository clean start");

        sqlproductsInStoresRepository.deleteAllInBatch();

        System.out.println("sqlproductsInStoresRepository clean finish");
        System.out.println("sqlproductRepository clean start");

        sqlproductRepository.deleteAllInBatch();

        System.out.println("sqlproductRepository clean finish");
        System.out.println("sqldiscountRepository clean start");

        sqldiscountRepository.deleteAllInBatch();

        System.out.println("sqldiscountRepository clean finish");
        System.out.println("sqlclientRepository clean start");

        sqlclientRepository.deleteAllInBatch();

        System.out.println("sqlclientRepository clean finish");
        System.out.println("sqlstoreRepository clean start");

        sqlstoreRepository.deleteAllInBatch();

        System.out.println("sqlstoreRepository clean finish");
        System.out.println("sqladdressRepository clean start");

        sqladdressRepository.deleteAllInBatch();

        System.out.println("sqladdressRepository clean finish");
        System.out.println("sql clean finish");
    }

    public void cleanMongo() {
        System.out.println("cleanMongo");
        System.out.println("mongoaddressRepository clean start");

        mongoaddressRepository.deleteAll();

        System.out.println("mongoaddressRepository clean finish");
        System.out.println("mongoclientRepository clean start");

        mongoclientRepository.deleteAll();

        System.out.println("mongoclientRepository clean finish");
        System.out.println("mongostoreRepository clean start");

        mongostoreRepository.deleteAll();

        System.out.println("mongostoreRepository clean finish");
        System.out.println("mongodiscountRepository clean start");

        mongodiscountRepository.deleteAll();

        System.out.println("mongodiscountRepository clean finish");
        System.out.println("mongoproductRepository clean start");

        mongoproductRepository.deleteAll();

        System.out.println("mongoproductRepository clean finish");
        System.out.println("mongocartRepository clean start");

        mongocartRepository.deleteAll();

        System.out.println("mongocartRepository clean finish");
        System.out.println("mongoorderRepository clean start");

        mongoorderRepository.deleteAll();

        System.out.println("mongoorderRepository clean finish");
        System.out.println("mongoshipmentRepository clean start");

        mongoshipmentRepository.deleteAll();

        System.out.println("mongoshipmentRepository clean finish");
        System.out.println("mongotransactionRepository clean start");

        mongotransactionRepository.deleteAll();

        System.out.println("mongotransactionRepository clean finish");
        System.out.println("mongoproductsInStoresRepository clean start");

        mongoproductsInStoresRepository.deleteAll();

        System.out.println("mongoproductsInStoresRepository clean finish");
        System.out.println("mogno clean finish");
    }

    public void cleanNeo() {
        System.out.println("cleanNeo");
        System.out.println("neoaddressRepository clean start");

        neoaddressRepository.deleteAll();

        System.out.println("neoaddressRepository clean finish");
        System.out.println("neoclientRepository clean start");

        neoclientRepository.deleteAll();

        System.out.println("neoclientRepository clean finish");
        System.out.println("neostoreRepository clean start");

        neostoreRepository.deleteAll();

        System.out.println("neostoreRepository clean finish");
        System.out.println("neodiscountRepository clean start");

        neodiscountRepository.deleteAll();

        System.out.println("neodiscountRepository clean finish");
        System.out.println("neoproductRepository clean start");

        neoproductRepository.deleteAll();

        System.out.println("neoproductRepository clean finish");
        System.out.println("neocartRepository clean start");

        neocartRepository.deleteAll();

        System.out.println("neocartRepository clean finish");
        System.out.println("neoorderRepository clean start");

        neoorderRepository.deleteAll();

        System.out.println("neoorderRepository clean finish");
        System.out.println("neoshipmentRepository clean start");

        neoshipmentRepository.deleteAll();

        System.out.println("neoshipmentRepository clean finish");
        System.out.println("neotransactionRepository clean start");

        neotransactionRepository.deleteAll();

        System.out.println("neotransactionRepository clean finish");
        System.out.println("neoproductsInStoresRepository clean start");

        neoproductsInStoresRepository.deleteAll();

        System.out.println("neoproductsInStoresRepository clean finish");
        System.out.println("neo clean finish");
    }

    public void cleanAll() {
        cleanSql();
        cleanMongo();
        cleanNeo();
    }
}
