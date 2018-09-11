package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoDiscount;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoDiscount;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlDiscount;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.AddProductToStoreScenarioMethods;

import java.util.Random;

@Component
public class AddProductToStoreScenario extends BaseScenario {
    private DataConfig dataConfig;
    private AddProductToStoreScenarioMethods addProductToStoreScenarioMethods;

    @Autowired
    public AddProductToStoreScenario(DataConfig dataConfig, AddProductToStoreScenarioMethods addProductToStoreScenarioMethods) {
        this.dataConfig = dataConfig;
        this.addProductToStoreScenarioMethods = addProductToStoreScenarioMethods;
    }

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        Long storeEntityId = getStoreEntityId();
        Long discountEntityId = getDiscountEntityId();
        Long quantity = 8l;

        SqlStore sqlStore = addProductToStoreScenarioMethods.sqlGetStoreByEntityId(storeEntityId);
        MongoStore mongoStore = addProductToStoreScenarioMethods.mongoGetStoreByEntityId(storeEntityId);
        NeoStore neoStore = addProductToStoreScenarioMethods.neoGetStoreByEntityId(storeEntityId);

        SqlDiscount sqlDiscount = addProductToStoreScenarioMethods.sqlGetDiscountByEntityId(discountEntityId);
        MongoDiscount mongoDiscount = addProductToStoreScenarioMethods.mongoGetDiscountByEntityId(discountEntityId);
        NeoDiscount neoDiscount = addProductToStoreScenarioMethods.neoGetDiscountByEntityId(discountEntityId);

        SqlProduct sqlProduct = addProductToStoreScenarioMethods.sqlCreateProduct(sqlDiscount);
        MongoProduct mongoProduct = addProductToStoreScenarioMethods.mongoCreateProduct(mongoDiscount);
        NeoProduct neoProduct = addProductToStoreScenarioMethods.neoCreateProduct(neoDiscount);

        addProductToStoreScenarioMethods.sqlCreateProductInStore(sqlProduct, sqlStore, quantity);
        addProductToStoreScenarioMethods.mongoCreateProductInStore(mongoProduct, mongoStore, quantity);
        addProductToStoreScenarioMethods.neoCreateProductInStore(neoProduct, neoStore, quantity);
    }

    @Override
    public void after() {

    }

    private Long getStoreEntityId() {
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }

    private Long getDiscountEntityId() {
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_DISCOUNTS));
    }
}
