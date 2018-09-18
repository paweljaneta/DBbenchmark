package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.simpleScenarios.LoggedBuyScenarioMethods;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class LoggedBuyScenario extends BaseScenario {

    private DataConfig dataConfig;
    private LoggedBuyScenarioMethods loggedBuyScenarioMethods;

    @Autowired
    public LoggedBuyScenario(DataConfig dataConfig, LoggedBuyScenarioMethods loggedBuyScenarioMethods) {
        this.dataConfig = dataConfig;
        this.loggedBuyScenarioMethods = loggedBuyScenarioMethods;
    }

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        Long storeEntityId = getStoreEntityId();

        List<String> emailList = getEmailList();
        int clientEmailIndex = getClientEmailIndex(emailList.size());

        SqlClient sqlClient = loggedBuyScenarioMethods.sqlGetClient(emailList.get(clientEmailIndex));
        MongoClient mongoClient = loggedBuyScenarioMethods.mongoGetClient(emailList.get(clientEmailIndex));
        NeoClient neoClient = loggedBuyScenarioMethods.neoGetClient(emailList.get(clientEmailIndex));

        SqlStore sqlStore = loggedBuyScenarioMethods.sqlGetStoreByEntityId(storeEntityId);
        MongoStore mongoStore = loggedBuyScenarioMethods.mongoGetStoreByEntityId(storeEntityId);
        NeoStore neoStore = loggedBuyScenarioMethods.neoGetStoreByEntityId(storeEntityId);

        List<SqlProduct> sqlProducts = loggedBuyScenarioMethods.sqlGetProductsFromStore(sqlStore);
        List<MongoProduct> mongoProducts = loggedBuyScenarioMethods.mongoGetProductsFromStore(mongoStore);
        List<NeoProduct> neoProducts = loggedBuyScenarioMethods.neoGetProductsFromStore(neoStore);

        SqlCart sqlCart = loggedBuyScenarioMethods.sqlCreateCart();
        MongoCart mongoCart = loggedBuyScenarioMethods.mongoCreateCart();
        NeoCart neoCart = loggedBuyScenarioMethods.neoCreateCart();

        List<Integer> productIndexes = getProductIndexes(sqlProducts.size(), 10);

        for (Integer productIndex : productIndexes) {
            loggedBuyScenarioMethods.sqlAddProductToCart(sqlCart, sqlProducts.get(productIndex));
            loggedBuyScenarioMethods.mongoAddProductToCart(mongoCart, mongoProducts.get(productIndex));
            loggedBuyScenarioMethods.neoAddProductToCart(neoCart, neoProducts.get(productIndex));
        }

        SqlOrder sqlOrder = loggedBuyScenarioMethods.sqlCreateOrder(sqlCart, sqlClient);
        MongoOrder mongoOrder = loggedBuyScenarioMethods.mongoCreateOrder(mongoCart, mongoClient);
        NeoOrder neoOrder = loggedBuyScenarioMethods.neoCreateOrder(neoCart, neoClient);

        loggedBuyScenarioMethods.sqlCreateTransaction(sqlOrder, sqlStore, DeliveryMode.SHIPMENT, sqlClient);
        loggedBuyScenarioMethods.mongoCreateTransaction(mongoOrder, mongoStore, DeliveryMode.SHIPMENT, mongoClient);
        loggedBuyScenarioMethods.neoCreateTransaction(neoOrder, neoStore, DeliveryMode.SHIPMENT, neoClient);

        loggedBuyScenarioMethods.sqlCreateShipment(sqlOrder);
        loggedBuyScenarioMethods.mongoCreateShipment(mongoOrder);
        loggedBuyScenarioMethods.neoCreateShipment(neoOrder);

        loggedBuyScenarioMethods.sqlSubtractProductQuantities(sqlOrder.getProducts(), sqlStore);
        loggedBuyScenarioMethods.mongoSubtractProductQuantities(mongoOrder.getProducts(), mongoStore);
        loggedBuyScenarioMethods.neoSubtractProductQuantities(neoOrder.getProducts(), neoStore);
    }

    @Override
    public void after() {

    }

    private int getClientEmailIndex(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    private List<Integer> getProductIndexes(int size, int number) {
        Random random = new Random();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            result.add(random.nextInt(size));
        }
        return result;
    }

    private Long getStoreEntityId() {
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }

    private List<String> getEmailList() {
        List<String> result = new ArrayList<>();
        String fileName = "clients.csv";
        Iterable<CSVRecord> records = loadCSV(fileName);
        for (CSVRecord record : records) {
            result.add(record.get("email"));
        }
        return result;
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
}
