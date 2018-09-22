package pl.polsl.paweljaneta.databasebenchmark.dataInsertion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.MongoDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.NeoDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.SqlDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.DatabaseCleaner;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.IdGenerator;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DataCreator {
    Logger logger = Logger.getLogger(this.getClass().getName());

    private final DatabaseToInsert databaseToInsert = DatabaseToInsert.ALL;

    private List<SqlAddress> sqlClientAddresses = new ArrayList<>();
    private List<SqlAddress> sqlStoreAddresses = new ArrayList<>();
    private List<SqlCart> sqlCarts = new ArrayList<>();
    private List<SqlClient> sqlClients = new ArrayList<>();
    private List<SqlDiscount> sqlDiscounts = new ArrayList<>();
    private List<SqlOrder> sqlOrders = new ArrayList<>();
    private List<SqlProduct> sqlProducts = new ArrayList<>();
    private List<SqlShipment> sqlShipments = new ArrayList<>();
    private List<SqlStore> sqlStores = new ArrayList<>();
    private List<SqlTransaction> sqlTransactions = new ArrayList<>();

    private List<NeoAddress> neoClientAddresses = new ArrayList<>();
    private List<NeoAddress> neoStoreAddresses = new ArrayList<>();
    private List<NeoCart> neoCarts = new ArrayList<>();
    private List<NeoClient> neoClients = new ArrayList<>();
    private List<NeoDiscount> neoDiscounts = new ArrayList<>();
    private List<NeoOrder> neoOrders = new ArrayList<>();
    private List<NeoProduct> neoProducts = new ArrayList<>();
    private List<NeoShipment> neoShipments = new ArrayList<>();
    private List<NeoStore> neoStores = new ArrayList<>();
    private List<NeoTransaction> neoTransactions = new ArrayList<>();

    private List<MongoAddress> mongoClientAddresses = new ArrayList<>();
    private List<MongoAddress> mongoStoreAddresses = new ArrayList<>();
    private List<MongoCart> mongoCarts = new ArrayList<>();
    private List<MongoClient> mongoClients = new ArrayList<>();
    private List<MongoDiscount> mongoDiscounts = new ArrayList<>();
    private List<MongoOrder> mongoOrders = new ArrayList<>();
    private List<MongoProduct> mongoProducts = new ArrayList<>();
    private List<MongoShipment> mongoShipments = new ArrayList<>();
    private List<MongoStore> mongoStores = new ArrayList<>();
    private List<MongoTransaction> mongoTransactions = new ArrayList<>();

    private int clientAddressSize = 0;
    private int storeAddressSize = 0;
    private int clientsSize = 0;
    private int storesSize = 0;
    private int discountsSize = 0;
    private int productsSize = 0;
    private int ordersSize = 0;

    private IdGenerator cartIdGenerator = new IdGenerator();
    private IdGenerator orderIdGenerator = new IdGenerator();
    private IdGenerator productsInStoresIdGenerator = new IdGenerator();

    @Autowired
    private SqlDataInsertor sqlDataInsertor;
    @Autowired
    private MongoDataInsertor mongoDataInsertor;
    @Autowired
    private NeoDataInsertor neoDataInsertor;

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private DataConfig dataConfig;

    //    @EventListener(ApplicationReadyEvent.class)
    public void createData() {
        createNewLists();
        if (databaseToInsert == DatabaseToInsert.ALL) {
            databaseCleaner.cleanAll();
        }
        if (databaseToInsert == DatabaseToInsert.SQL) {
            databaseCleaner.cleanSql();
        }
        if (databaseToInsert == DatabaseToInsert.MONGO) {
            databaseCleaner.cleanMongo();
        }
        if (databaseToInsert == DatabaseToInsert.NEO4J) {
            databaseCleaner.cleanNeo();
        }

        createStoreAddressData();
        createClientAddressData();
        createClientData();
        createStoreData();
        createDiscountData();
        createProductData();
        createCartData();
        createOrderData();
        createShipmentData();
        createTransactionData();
        createProductsInStoresData();
        createNewLists();
        logger.log(Level.INFO, "Data loading finished");
    }

    private void createNewLists() {
        sqlClientAddresses = new ArrayList<>();
        sqlStoreAddresses = new ArrayList<>();
        sqlCarts = new ArrayList<>();
        sqlClients = new ArrayList<>();
        sqlDiscounts = new ArrayList<>();
        sqlOrders = new ArrayList<>();
        sqlProducts = new ArrayList<>();
        sqlShipments = new ArrayList<>();
        sqlStores = new ArrayList<>();
        sqlTransactions = new ArrayList<>();

        neoClientAddresses = new ArrayList<>();
        neoStoreAddresses = new ArrayList<>();
        neoCarts = new ArrayList<>();
        neoClients = new ArrayList<>();
        neoDiscounts = new ArrayList<>();
        neoOrders = new ArrayList<>();
        neoProducts = new ArrayList<>();
        neoShipments = new ArrayList<>();
        neoStores = new ArrayList<>();
        neoTransactions = new ArrayList<>();

        mongoClientAddresses = new ArrayList<>();
        mongoStoreAddresses = new ArrayList<>();
        mongoCarts = new ArrayList<>();
        mongoClients = new ArrayList<>();
        mongoDiscounts = new ArrayList<>();
        mongoOrders = new ArrayList<>();
        mongoProducts = new ArrayList<>();
        mongoShipments = new ArrayList<>();
        mongoStores = new ArrayList<>();
        mongoTransactions = new ArrayList<>();
    }

    private Iterable<CSVRecord> loadCSV(String fileName) {
        Reader in = new InputStreamReader(this.getClass().getResourceAsStream("/data/" + fileName));
        try {
            CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(';');
            return format.parse(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Integer> fillListWithIndexes(int endIndex) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i <= endIndex; i++) {
            result.add(i);
        }
        return result;
    }

    private List<Integer> fillListWithRandomIndexes(int endIndex, int count) {
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            result.add(random.nextInt(endIndex + 1));
        }
        return result;
    }

    private List<Integer> fillListWithRandomUniqueIndexes(int endIndex, int count) {
        ArrayList<Integer> result = new ArrayList<>();
        Random random = new Random();
        int index = 0;
        while (index < count) {
            int rnd = random.nextInt(endIndex + 1);
            if (!result.contains(rnd)) {
                result.add(rnd);
                index++;
            }
        }
        return result;
    }

    private List<Long> fillListWithGeneratedEntityIds(int count, IdGenerator generator) {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(generator.getId());
        }
        return result;
    }

    private void createClientAddressData() {
        String fileName = "addressClients.csv";
        Iterable<CSVRecord> records;

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertClientAddressData(sqlClientAddresses, records);
            clientAddressSize = sqlClientAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertClientAddressData(mongoClientAddresses, records);
            clientAddressSize = mongoClientAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertClientAddressData(neoClientAddresses, records);
            clientAddressSize = neoClientAddresses.size();
        }
    }

    private void createStoreAddressData() {
        String fileName = "addressStores.csv";
        Iterable<CSVRecord> records;

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertStoreAddressData(sqlStoreAddresses, records);
            storeAddressSize = sqlStoreAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertStoreAddressData(mongoStoreAddresses, records);
            storeAddressSize = mongoStoreAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertStoreAddressData(neoStoreAddresses, records);
            storeAddressSize = neoStoreAddresses.size();
        }
    }

    private void createClientData() {
        String fileName = "clients.csv";
        Iterable<CSVRecord> records;
        List<Integer> addressIndexes = fillListWithIndexes(clientAddressSize - 1);
        Collections.shuffle(addressIndexes);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertClientData(sqlClients, records, sqlClientAddresses, addressIndexes);
            clientsSize = sqlClients.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertClientData(mongoClients, records, mongoClientAddresses, addressIndexes);
            clientsSize = mongoClients.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertClientData(neoClients, records, neoClientAddresses, addressIndexes);
            clientsSize = neoClients.size();
        }
    }

    private void createStoreData() {
        String fileName = "stores.csv";
        Iterable<CSVRecord> records;
        List<Integer> addressIndexes = fillListWithIndexes(storeAddressSize - 1);
        Collections.shuffle(addressIndexes);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertStoreData(sqlStores, records, sqlStoreAddresses, addressIndexes);
            storesSize = sqlStores.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertStoreData(mongoStores, records, mongoStoreAddresses, addressIndexes);
            storesSize = mongoStores.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertStoreData(neoStores, records, neoStoreAddresses, addressIndexes);
            storesSize = neoStores.size();
        }
    }

    private void createDiscountData() {
        String fileName = "discounts.csv";
        Iterable<CSVRecord> records;

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertDiscountData(sqlDiscounts, records);
            discountsSize = sqlDiscounts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertDiscountData(mongoDiscounts, records);
            discountsSize = mongoDiscounts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertDiscountData(neoDiscounts, records);
            discountsSize = neoDiscounts.size();
        }
    }

    private void createProductData() {
        String fileName = "products.csv";
        Iterable<CSVRecord> records;
        List<Integer> discountIndexes = fillListWithRandomIndexes(discountsSize - 1, dataConfig.NO_OF_PRODUCTS);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertProductData(sqlProducts, records, sqlDiscounts, discountIndexes);
            productsSize = sqlProducts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertProductData(mongoProducts, records, mongoDiscounts, discountIndexes);
            productsSize = mongoProducts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertProductData(neoProducts, records, neoDiscounts, discountIndexes);
            productsSize = neoProducts.size();
        }
    }

    private void createCartData() {
        Random random = new Random();
        List<Integer> clientIndexes = fillListWithRandomIndexes(clientsSize - 1, dataConfig.NO_OF_CARTS);
        List<List<Integer>> productIndexes = new ArrayList<>();
        List<Long> entityIds = fillListWithGeneratedEntityIds(dataConfig.NO_OF_CARTS, cartIdGenerator);

        for (int i = 0; i < dataConfig.NO_OF_CARTS; i++) {
            int productNumber = 1 + random.nextInt(dataConfig.MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER);
            List<Integer> list = fillListWithRandomUniqueIndexes(productsSize - 1, productNumber);
            productIndexes.add(list);
        }

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertCartData(sqlCarts, sqlClients, clientIndexes, sqlProducts, productIndexes, entityIds);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertCartData(mongoCarts, mongoClients, clientIndexes, mongoProducts, productIndexes, entityIds);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertCartData(neoCarts, neoClients, clientIndexes, neoProducts, productIndexes, entityIds);
        }
    }

    private void createOrderData() {
        Random random = new Random();
        List<Integer> clientIndexes = fillListWithRandomIndexes(clientsSize - 1, dataConfig.NO_OF_ORDERS);
        List<List<Integer>> productIndexes = new ArrayList<>();
        List<Long> entityIds = fillListWithGeneratedEntityIds(dataConfig.NO_OF_ORDERS, orderIdGenerator);

        for (int i = 0; i < dataConfig.NO_OF_ORDERS; i++) {
            int productNumber = 1 + random.nextInt(dataConfig.MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER);
            List<Integer> list = fillListWithRandomUniqueIndexes(productsSize - 1, productNumber);
            productIndexes.add(list);
        }

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertOrderData(sqlOrders, sqlClients, clientIndexes, sqlProducts, productIndexes, entityIds);
            ordersSize = sqlOrders.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertOrderData(mongoOrders, mongoClients, clientIndexes, mongoProducts, productIndexes, entityIds);
            ordersSize = mongoOrders.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertOrderData(neoOrders, neoClients, clientIndexes, neoProducts, productIndexes, entityIds);
            ordersSize = neoOrders.size();
        }
    }

    private void createShipmentData() {
        String fileName = "shipments.csv";
        Iterable<CSVRecord> records;
        List<Integer> ordersIndexes = fillListWithIndexes(ordersSize - 1);
        Collections.shuffle(ordersIndexes);


        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertShipmentData(sqlShipments, records, sqlOrders, ordersIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertShipmentData(mongoShipments, records, mongoOrders, ordersIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertShipmentData(neoShipments, records, neoOrders, ordersIndexes);
        }
    }

    private void createTransactionData() {
        Random random = new Random();
        String fileName = "transactions.csv";
        Iterable<CSVRecord> records;

        List<List<Integer>> productIndexes = new ArrayList<>();
        List<DeliveryMode> deliveryModes = new ArrayList<>();
        DeliveryMode[] deliveryModeValues = DeliveryMode.values();
        List<Integer> clientIndexes = fillListWithRandomIndexes(clientsSize - 1, dataConfig.NO_OF_TRANSACTIONS);

        for (int i = 0; i < dataConfig.NO_OF_TRANSACTIONS; i++) {
            int productNumber = 1 + random.nextInt(dataConfig.MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER);
            List<Integer> list = fillListWithRandomUniqueIndexes(productsSize - 1, productNumber);
            productIndexes.add(list);

            int rnd = random.nextInt(DeliveryMode.values().length);
            deliveryModes.add(deliveryModeValues[rnd]);
        }

        List<Integer> storeIds = fillListWithRandomIndexes(storesSize - 1, dataConfig.NO_OF_TRANSACTIONS);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            records = loadCSV(fileName);
            sqlDataInsertor.insertTransactionData(sqlTransactions, records, sqlStores, deliveryModes, storeIds, sqlProducts, productIndexes, sqlClients, clientIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            records = loadCSV(fileName);
            mongoDataInsertor.insertTransactionData(mongoTransactions, records, mongoStores, deliveryModes, storeIds, mongoProducts, productIndexes, mongoClients, clientIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            records = loadCSV(fileName);
            neoDataInsertor.insertTransactionData(neoTransactions, records, neoStores, deliveryModes, storeIds, neoProducts, productIndexes, neoClients, clientIndexes);
        }
    }

    private void createProductsInStoresData() {
        Random random = new Random();

        List<List<Integer>> productIndexes = new ArrayList<>();
        List<List<Long>> quantities = new ArrayList<>();
        List<Long> entityIds = new ArrayList<>();

        for (int i = 0; i < storesSize; i++) {
            int noOfProducts = dataConfig.MIN_NO_OF_PRODUCTS_IN_STORE + random.nextInt(dataConfig.MAX_NO_OF_PRODUCTS_IN_STORE - dataConfig.MIN_NO_OF_PRODUCTS_IN_STORE + 1);
            List<Integer> listOfProductIndexes = fillListWithRandomUniqueIndexes(productsSize - 1, noOfProducts);
            productIndexes.add(listOfProductIndexes);

            List<Long> listOfQuantities = new ArrayList<>();
            for (Integer listOfProductIndex : listOfProductIndexes) {
                long quantity = 1 + random.nextInt(dataConfig.MAX_PRODUCT_QUANTITY);
                listOfQuantities.add(quantity);

                entityIds.add(productsInStoresIdGenerator.getId());
            }
            quantities.add(listOfQuantities);
        }

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertProductsInStores(sqlStores, sqlProducts, productIndexes, quantities, entityIds);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertProductsInStores(mongoStores, mongoProducts, productIndexes, quantities, entityIds);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertProductsInStores(neoStores, neoProducts, productIndexes, quantities, entityIds);
        }
    }


    enum DatabaseToInsert {
        ALL, SQL, MONGO, NEO4J
    }
}
