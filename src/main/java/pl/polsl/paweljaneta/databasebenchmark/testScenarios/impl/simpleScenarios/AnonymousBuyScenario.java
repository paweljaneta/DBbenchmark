package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoCart;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoOrder;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoCart;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoOrder;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlCart;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlOrder;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.simpleScenarios.AnonymousBuyScenarioMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AnonymousBuyScenario extends BaseScenario {
    private DataConfig dataConfig;
    private AnonymousBuyScenarioMethods anonymousBuyScenarioMethods;

    @Autowired
    public AnonymousBuyScenario(DataConfig dataConfig, AnonymousBuyScenarioMethods anonymousBuyScenarioMethods) {
        this.dataConfig = dataConfig;
        this.anonymousBuyScenarioMethods = anonymousBuyScenarioMethods;
    }

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        Long storeEntityId = getStoreEntityId();

        SqlStore sqlStore = anonymousBuyScenarioMethods.sqlGetStoreByEntityId(storeEntityId);
        MongoStore mongoStore = anonymousBuyScenarioMethods.mongoGetStoreByEntityId(storeEntityId);
        NeoStore neoStore = anonymousBuyScenarioMethods.neoGetStoreByEntityId(storeEntityId);

        List<SqlProduct> sqlProducts = anonymousBuyScenarioMethods.sqlGetProductsFromStore(sqlStore);
        List<MongoProduct> mongoProducts = anonymousBuyScenarioMethods.mongoGetProductsFromStore(mongoStore);
        List<NeoProduct> neoProducts = anonymousBuyScenarioMethods.neoGetProductsFromStore(neoStore);

        SqlCart sqlCart = anonymousBuyScenarioMethods.sqlCreateCart();
        MongoCart mongoCart = anonymousBuyScenarioMethods.mongoCreateCart();
        NeoCart neoCart = anonymousBuyScenarioMethods.neoCreateCart();

        List<Integer> productIndexes = getProductIndexes(sqlProducts.size(), 10);

        for (Integer productIndex : productIndexes) {
            anonymousBuyScenarioMethods.sqlAddProductToCart(sqlCart, sqlProducts.get(productIndex));
            anonymousBuyScenarioMethods.mongoAddProductToCart(mongoCart, mongoProducts.get(productIndex));
            anonymousBuyScenarioMethods.neoAddProductToCart(neoCart, neoProducts.get(productIndex));
        }

        SqlOrder sqlOrder = anonymousBuyScenarioMethods.sqlCreateOrder(sqlCart);
        MongoOrder mongoOrder = anonymousBuyScenarioMethods.mongoCreateOrder(mongoCart);
        NeoOrder neoOrder = anonymousBuyScenarioMethods.neoCreateOrder(neoCart);

        anonymousBuyScenarioMethods.sqlCreateTransaction(sqlOrder, sqlStore, DeliveryMode.SHIPMENT);
        anonymousBuyScenarioMethods.mongoCreateTransaction(mongoOrder, mongoStore, DeliveryMode.SHIPMENT);
        anonymousBuyScenarioMethods.neoCreateTransaction(neoOrder, neoStore, DeliveryMode.SHIPMENT);

        anonymousBuyScenarioMethods.sqlCreateShipment(sqlOrder);
        anonymousBuyScenarioMethods.mongoCreateShipment(mongoOrder);
        anonymousBuyScenarioMethods.neoCreateShipment(neoOrder);

        anonymousBuyScenarioMethods.sqlSubtractProductQuantities(sqlOrder.getProducts(), sqlStore);
        anonymousBuyScenarioMethods.mongoSubtractProductQuantities(mongoOrder.getProducts(), mongoStore);
        anonymousBuyScenarioMethods.neoSubtractProductQuantities(neoOrder.getProducts(), neoStore);
    }

    private List<Integer> getProductIndexes(int size, int number) {
        Random random = new Random();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            result.add(random.nextInt(size));
        }
        return result;
    }

    @Override
    public void after() {

    }

    private Long getStoreEntityId() {
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }
}
