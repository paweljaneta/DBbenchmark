package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class LoggedBuyScenarioMethods {
    @Autowired
    private SqlCartRepository sqlCartRepository;
    @Autowired
    private SqlOrderRepository sqlOrderRepository;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlShipmentRepository sqlShipmentRepository;
    @Autowired
    private SqlProductsInStoresRepository sqlProductsInStoresRepository;
    @Autowired
    private SqlStoreRepository sqlStoreRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;
    @Autowired
    private SqlClientRepository sqlClientRepository;

    @Autowired
    private MongoCartRepository mongoCartRepository;
    @Autowired
    private MongoOrderRepository mongoOrderRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoShipmentRepository mongoShipmentRepository;
    @Autowired
    private MongoProductsInStoresRepository mongoProductsInStoresRepository;
    @Autowired
    private MongoStoreRepository mongoStoreRepository;
    @Autowired
    private MongoProductRepository mongoProductRepository;
    @Autowired
    private MongoClientRepository mongoClientRepository;

    @Autowired
    private NeoCartRepository neoCartRepository;
    @Autowired
    private NeoOrderRepository neoOrderRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;
    @Autowired
    private NeoShipmentRepository neoShipmentRepository;
    @Autowired
    private NeoProductsInStoresRepository neoProductsInStoresRepository;
    @Autowired
    private NeoStoreRepository neoStoreRepository;
    @Autowired
    private NeoProductRepository neoProductRepository;
    @Autowired
    private NeoClientRepository neoClientRepository;

    @ExecTimeMeasure
    public SqlClient sqlGetClient(String email) {
        return sqlClientRepository.findByEmail(email);
    }

    @ExecTimeMeasure
    public SqlStore sqlGetStoreByEntityId(Long storeEntityId) {
        return sqlStoreRepository.findFirstByEntityId(storeEntityId);
    }

    @ExecTimeMeasure
    public List<SqlProduct> sqlGetProductsFromStore(SqlStore store) {
        List<Long> productIds = new ArrayList<>();
        List<SqlProductsInStores> allByStoreId = sqlProductsInStoresRepository.findAllByStoreId(store.getId());
        for (SqlProductsInStores sqlProductsInStores : allByStoreId) {
            productIds.add(sqlProductsInStores.getProductId());
        }
        return sqlProductRepository.findByProductIdIn(productIds);
    }

    @ExecTimeMeasure
    public SqlCart sqlCreateCart() {
        return new SqlCart();
    }

    @ExecTimeMeasure
    public void sqlAddProductToCart(SqlCart cart, SqlProduct product) {
        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<SqlProduct>());
        }
        cart.getProducts().add(product);
        sqlCartRepository.save(cart);
    }

    @ExecTimeMeasure
    public SqlOrder sqlCreateOrder(SqlCart cart, SqlClient client) {
        SqlOrder order = new SqlOrder();
        order.setProducts(cart.getProducts());
        order.setClient(client);
        sqlOrderRepository.save(order);
        return order;
    }

    @ExecTimeMeasure
    public SqlTransaction sqlCreateTransaction(SqlOrder order, SqlStore store, DeliveryMode deliveryMode) {
        SqlTransaction transaction = new SqlTransaction();
        transaction.setProducts(order.getProducts());
        transaction.setStore(store);
        transaction.setDeliveryMode(deliveryMode);
        transaction.setDate(new Date());
        sqlTransactionRepository.save(transaction);
        return transaction;
    }

    @ExecTimeMeasure
    public void sqlSubtractProductQuantities(List<SqlProduct> products, SqlStore store) {
        for (SqlProduct product : products) {
            SqlProductsInStores productInStore = sqlProductsInStoresRepository.findFirstByProductIdAndStoreId(product.getProductId(), store.getId());
            Long quantity = productInStore.getQuantity();
            quantity--;
            productInStore.setQuantity(quantity);
            sqlProductsInStoresRepository.save(productInStore);
        }
    }

    @ExecTimeMeasure
    public void sqlCreateShipment(SqlOrder order) {
        SqlShipment shipment = new SqlShipment();
        shipment.setOrder(order);
        shipment.setShipmentDetails("Created by app");
        shipment.setTracingNumber("123456789");
        sqlShipmentRepository.save(shipment);
    }


    @ExecTimeMeasure
    public MongoStore mongoGetStoreByEntityId(Long storeEntityId) {
        return mongoStoreRepository.findFirstByEntityId(storeEntityId);
    }

    @ExecTimeMeasure
    public List<MongoProduct> mongoGetProductsFromStore(MongoStore store) {
        List<MongoProduct> products = new ArrayList<>();
        List<MongoProductsInStores> allByStoreId = mongoProductsInStoresRepository.findAllByStoreId(store.getId());
        for (MongoProductsInStores mongoProductsInStores : allByStoreId) {
            products.add(mongoProductRepository.findById(mongoProductsInStores.getProductId()).get());
        }
        return products;
    }

    @ExecTimeMeasure
    public MongoCart mongoCreateCart() {
        return new MongoCart();
    }

    @ExecTimeMeasure
    public void mongoAddProductToCart(MongoCart cart, MongoProduct product) {
        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<MongoProduct>());
        }
        cart.getProducts().add(product);
        mongoCartRepository.save(cart);
    }

    @ExecTimeMeasure
    public MongoOrder mongoCreateOrder(MongoCart cart, MongoClient client) {
        MongoOrder order = new MongoOrder();
        order.setProducts(cart.getProducts());
        order.setClient(client);
        mongoOrderRepository.save(order);
        return order;
    }

    @ExecTimeMeasure
    public MongoTransaction mongoCreateTransaction(MongoOrder order, MongoStore store, DeliveryMode deliveryMode) {
        MongoTransaction transaction = new MongoTransaction();
        transaction.setProducts(order.getProducts());
        transaction.setStore(store);
        transaction.setDeliveryMode(deliveryMode);
        transaction.setDate(new Date());
        mongoTransactionRepository.save(transaction);
        return transaction;
    }

    @ExecTimeMeasure
    public void mongoSubtractProductQuantities(List<MongoProduct> products, MongoStore store) {
        for (MongoProduct product : products) {
            MongoProductsInStores productInStore = mongoProductsInStoresRepository.findFirstByProductIdAndStoreId(product.getId(), store.getId());
            Long quantity = productInStore.getQuantity();
            quantity--;
            productInStore.setQuantity(quantity);
            mongoProductsInStoresRepository.save(productInStore);
        }
    }

    @ExecTimeMeasure
    public void mongoCreateShipment(MongoOrder order) {
        MongoShipment shipment = new MongoShipment();
        shipment.setOrder(order);
        shipment.setShipmentDetails("Created by app");
        shipment.setTracingNumber("123456789");
        mongoShipmentRepository.save(shipment);
    }

    @ExecTimeMeasure
    public MongoClient mongoGetClient(String email) {
        return mongoClientRepository.findByEmail(email);
    }

    @ExecTimeMeasure
    public NeoStore neoGetStoreByEntityId(Long storeEntityId) {
        return neoStoreRepository.findFirstByEntityId(storeEntityId);
    }

    @ExecTimeMeasure
    public List<NeoProduct> neoGetProductsFromStore(NeoStore store) {
        List<NeoProduct> products = new ArrayList<>();
        List<NeoProductsInStores> allByStoreId = neoProductsInStoresRepository.findByStoreId(store.getId());
        for (NeoProductsInStores neoProductsInStores : allByStoreId) {
            // products.add(neoProductRepository.findById(neoProductsInStores.getProduct().getId()).get());
            products.add(neoProductsInStores.getProduct());
        }
        return products;
    }

    @ExecTimeMeasure
    public NeoCart neoCreateCart() {
        return new NeoCart();
    }

    @ExecTimeMeasure
    public void neoAddProductToCart(NeoCart cart, NeoProduct product) {
        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<NeoProduct>());
        }
        cart.getProducts().add(product);
        neoCartRepository.save(cart);
    }

    @ExecTimeMeasure
    public NeoOrder neoCreateOrder(NeoCart cart, NeoClient client) {
        NeoOrder order = new NeoOrder();
        order.setProducts(cart.getProducts());
        order.setClient(client);
        neoOrderRepository.save(order);
        return order;
    }

    @ExecTimeMeasure
    public NeoTransaction neoCreateTransaction(NeoOrder order, NeoStore store, DeliveryMode deliveryMode) {
        NeoTransaction transaction = new NeoTransaction();
        transaction.setProducts(order.getProducts());
        transaction.setStore(store);
        transaction.setDeliveryMode(deliveryMode);
        transaction.setDate(new Date());
        neoTransactionRepository.save(transaction);
        return transaction;
    }

    @ExecTimeMeasure
    public void neoSubtractProductQuantities(List<NeoProduct> products, NeoStore store) {
        for (NeoProduct product : products) {
            NeoProductsInStores productInStore = neoProductsInStoresRepository.findFirstByProductIdAndStoreId(product.getId(), store.getId());
            Long quantity = productInStore.getQuantity();
            quantity--;
            productInStore.setQuantity(quantity);
            neoProductsInStoresRepository.save(productInStore);
        }
    }

    @ExecTimeMeasure
    public void neoCreateShipment(NeoOrder order) {
        NeoShipment shipment = new NeoShipment();
        shipment.setOrder(order);
        shipment.setShipmentDetails("Created by app");
        shipment.setTracingNumber("123456789");
        neoShipmentRepository.save(shipment);
    }

    @ExecTimeMeasure
    public NeoClient neoGetClient(String email) {
        return neoClientRepository.findByEmail(email);
    }

}
