package pl.polsl.paweljaneta.databasebenchmark.dataInsertion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl.MongoDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl.NeoDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl.SqlDataInsertor;
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

@Component
public class DataCreator {
    private final DatabaseToInsert databaseToInsert = DatabaseToInsert.NEO4J;

    private final int NO_OF_CARTS = 20000;
    private final int NO_OF_ORDERS = 10000;
    private final int NO_OF_TRANSACTIONS = 10000;
    private final int MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER = 15;
    private final int MIN_NO_OF_PRODUCTS_IN_STORE = 100;
    private final int MAX_NO_OF_PRODUCTS_IN_STORE = 5000;
    private final int MAX_PRODUCT_QUANTITY = 100;
    private final int NO_OF_PRODUCTS = 50000;

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

    @Autowired
    private SqlDataInsertor sqlDataInsertor;
    @Autowired
    private MongoDataInsertor mongoDataInsertor;
    @Autowired
    private NeoDataInsertor neoDataInsertor;

    @EventListener(ApplicationReadyEvent.class)
    public void createData() {
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

        System.out.println("Data loading finished");
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

    private void createClientAddressData() {
        Iterable<CSVRecord> records = loadCSV("addressClients.csv");

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertClientAddressData(sqlClientAddresses, records);
            clientAddressSize = sqlClientAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertClientAddressData(mongoClientAddresses, records);
            clientAddressSize = mongoClientAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertClientAddressData(neoClientAddresses, records);
            clientAddressSize = neoClientAddresses.size();
        }
    }

    private void createStoreAddressData() {
        Iterable<CSVRecord> records = loadCSV("addressStores.csv");

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertStoreAddressData(sqlStoreAddresses, records);
            storeAddressSize = sqlStoreAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertStoreAddressData(mongoStoreAddresses, records);
            storeAddressSize = mongoStoreAddresses.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertStoreAddressData(neoStoreAddresses, records);
            storeAddressSize = neoStoreAddresses.size();
        }
    }

    private void createClientData() {
        Iterable<CSVRecord> records = loadCSV("clients.csv");
        List<Integer> addressIndexes = fillListWithIndexes(clientAddressSize - 1);
        Collections.shuffle(addressIndexes);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertClientData(sqlClients, records, sqlClientAddresses, addressIndexes);
            clientsSize = sqlClients.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertClientData(mongoClients, records, mongoClientAddresses, addressIndexes);
            clientsSize = mongoClients.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertClientData(neoClients, records, neoClientAddresses, addressIndexes);
            clientsSize = neoClients.size();
        }
    }

    private void createStoreData() {
        Iterable<CSVRecord> records = loadCSV("stores.csv");
        List<Integer> addressIndexes = fillListWithIndexes(storeAddressSize - 1);
        Collections.shuffle(addressIndexes);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertStoreData(sqlStores, records, sqlStoreAddresses, addressIndexes);
            storesSize = sqlStores.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertStoreData(mongoStores, records, mongoStoreAddresses, addressIndexes);
            storesSize = mongoStores.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertStoreData(neoStores, records, neoStoreAddresses, addressIndexes);
            storesSize = neoStores.size();
        }
    }

    private void createDiscountData() {
        Iterable<CSVRecord> records = loadCSV("discounts.csv");

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertDiscountData(sqlDiscounts, records);
            discountsSize = sqlDiscounts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertDiscountData(mongoDiscounts, records);
            discountsSize = mongoDiscounts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertDiscountData(neoDiscounts, records);
            discountsSize = neoDiscounts.size();
        }
    }

    private void createProductData() {
        Iterable<CSVRecord> records = loadCSV("products.csv");
        List<Integer> discountIndexes = fillListWithRandomIndexes(discountsSize - 1, NO_OF_PRODUCTS);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertProductData(sqlProducts, records, sqlDiscounts, discountIndexes);
            productsSize = sqlProducts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertProductData(mongoProducts, records, mongoDiscounts, discountIndexes);
            productsSize = mongoProducts.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertProductData(neoProducts, records, neoDiscounts, discountIndexes);
            productsSize = neoProducts.size();
        }
    }

    private void createCartData() {
        Random random = new Random();
        List<Integer> clientIndexes = fillListWithRandomIndexes(clientsSize- 1, NO_OF_CARTS);
        List<List<Integer>> productIndexes = new ArrayList<>();

        for (int i = 0; i < NO_OF_CARTS; i++) {
            int productNumber = 1 + random.nextInt(MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER);
            List<Integer> list = fillListWithRandomUniqueIndexes(productsSize - 1, productNumber);
            productIndexes.add(list);
        }

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertCartData(sqlCarts, sqlClients, clientIndexes, sqlProducts, productIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertCartData(mongoCarts, mongoClients, clientIndexes, mongoProducts, productIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertCartData(neoCarts, neoClients, clientIndexes, neoProducts, productIndexes);
        }
    }

    private void createOrderData() {
        Random random = new Random();
        List<Integer> clientIndexes = fillListWithRandomIndexes(clientsSize - 1, NO_OF_ORDERS);
        List<List<Integer>> productIndexes = new ArrayList<>();

        for (int i = 0; i < NO_OF_ORDERS; i++) {
            int productNumber = 1 + random.nextInt(MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER);
            List<Integer> list = fillListWithRandomUniqueIndexes(productsSize - 1, productNumber);
            productIndexes.add(list);
        }

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertOrderData(sqlOrders, sqlClients, clientIndexes, sqlProducts, productIndexes);
            ordersSize = sqlOrders.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertOrderData(mongoOrders, mongoClients, clientIndexes, mongoProducts, productIndexes);
            ordersSize = mongoOrders.size();
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertOrderData(neoOrders, neoClients, clientIndexes, neoProducts, productIndexes);
            ordersSize = neoOrders.size();
        }
    }

    private void createShipmentData() {
        Iterable<CSVRecord> records = loadCSV("shipments.csv");
        List<Integer> ordersIndexes = fillListWithIndexes(ordersSize - 1);
        Collections.shuffle(ordersIndexes);


        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertShipmentData(sqlShipments, records, sqlOrders, ordersIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertShipmentData(mongoShipments, records, mongoOrders, ordersIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertShipmentData(neoShipments, records, neoOrders, ordersIndexes);
        }
    }

    private void createTransactionData() {
        Random random = new Random();
        Iterable<CSVRecord> records = loadCSV("transactions.csv");

        List<List<Integer>> productIndexes = new ArrayList<>();
        List<DeliveryMode> deliveryModes = new ArrayList<>();
        DeliveryMode[] deliveryModeValues = DeliveryMode.values();

        for (int i = 0; i < NO_OF_TRANSACTIONS; i++) {
            int productNumber = 1 + random.nextInt(MAX_NO_OF_PRODUCTS_IN_TRANSACTION_CART_ORDER);
            List<Integer> list = fillListWithRandomUniqueIndexes(productsSize - 1, productNumber);
            productIndexes.add(list);

            int rnd = random.nextInt(DeliveryMode.values().length);
            deliveryModes.add(deliveryModeValues[rnd]);
        }

        List<Integer> storeIds = fillListWithRandomIndexes(storesSize - 1, NO_OF_TRANSACTIONS);

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertTransactionData(sqlTransactions, records, sqlStores, deliveryModes, storeIds, sqlProducts, productIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertTransactionData(mongoTransactions, records, mongoStores, deliveryModes, storeIds, mongoProducts, productIndexes);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertTransactionData(neoTransactions, records, neoStores, deliveryModes, storeIds, neoProducts, productIndexes);
        }
    }

    private void createProductsInStoresData() {
        Random random = new Random();

        List<List<Integer>> productIndexes = new ArrayList<>();
        List<List<Long>> quantities = new ArrayList<>();
        for (int i = 0; i < storesSize; i++) {
            int noOfProducts = MIN_NO_OF_PRODUCTS_IN_STORE + random.nextInt(MAX_NO_OF_PRODUCTS_IN_STORE - MIN_NO_OF_PRODUCTS_IN_STORE + 1);
            List<Integer> listOfProductIndexes = fillListWithRandomUniqueIndexes(productsSize - 1, noOfProducts);
            productIndexes.add(listOfProductIndexes);

            List<Long> listOfQuantities = new ArrayList<>();
            for (Integer listOfProductIndex : listOfProductIndexes) {
                long quantity = 1 + random.nextInt(MAX_PRODUCT_QUANTITY);
                listOfQuantities.add(quantity);
            }
            quantities.add(listOfQuantities);
        }

        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.SQL)) {
            sqlDataInsertor.insertProductsInStores(sqlStores, sqlProducts, productIndexes, quantities);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.MONGO)) {
            mongoDataInsertor.insertProductsInStores(mongoStores, mongoProducts, productIndexes, quantities);
        }
        if (databaseToInsert.equals(DatabaseToInsert.ALL) || databaseToInsert.equals(DatabaseToInsert.NEO4J)) {
            neoDataInsertor.insertProductsInStores(neoStores, neoProducts, productIndexes, quantities);
        }
    }


    enum DatabaseToInsert {
        ALL, SQL, MONGO, NEO4J
    }
}
