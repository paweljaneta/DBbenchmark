package pl.polsl.paweljaneta.databasebenchmark.dataInsertion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class DataCreator {
    private List<SqlAddress> sqlAddresses = new LinkedList<>();
    private List<SqlCart> sqlCarts = new LinkedList<>();
    private List<SqlClient> sqlClients = new LinkedList<>();
    private List<SqlDiscount> sqlDiscounts = new LinkedList<>();
    private List<SqlOrder> sqlOrders = new LinkedList<>();
    private List<SqlProduct> sqlProducts = new LinkedList<>();
    private List<SqlShipment> sqlShipments = new LinkedList<>();
    private List<SqlStore> sqlStores = new LinkedList<>();
    private List<SqlTransaction> sqlTransactions = new LinkedList<>();
    private List<NeoAddress> neoAddresses = new LinkedList<>();
    private List<NeoCart> neoCarts = new LinkedList<>();
    private List<NeoClient> neoClients = new LinkedList<>();
    private List<NeoDiscount> neoDiscounts = new LinkedList<>();
    private List<NeoOrder> neoOrders = new LinkedList<>();
    private List<NeoProduct> neoProducts = new LinkedList<>();
    private List<NeoShipment> neoShipments = new LinkedList<>();
    private List<NeoStore> neoStores = new LinkedList<>();
    private List<NeoTransaction> neoTransactions = new LinkedList<>();
    private List<MongoAddress> mongoAddresses = new LinkedList<>();
    private List<MongoCart> mongoCarts = new LinkedList<>();
    private List<MongoClient> mongoClients = new LinkedList<>();
    private List<MongoDiscount> mongoDiscounts = new LinkedList<>();
    private List<MongoOrder> mongoOrders = new LinkedList<>();
    private List<MongoProduct> mongoProducts = new LinkedList<>();
    private List<MongoShipment> mongoShipments = new LinkedList<>();
    private List<MongoStore> mongoStores = new LinkedList<>();
    private List<MongoTransaction> mongoTransactions = new LinkedList<>();

    @EventListener(ApplicationReadyEvent.class)
    public void createData() {
        createStoreAddressData();
    }

    private Iterable<CSVRecord> loadCSV(String fileName){
        Reader in = new InputStreamReader(this.getClass().getResourceAsStream("/data/"+fileName));
        try {
           CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(';');
           return format.parse(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void createClientAddressData(){

    }

    private void createStoreAddressData(){
        Iterable<CSVRecord> records = loadCSV("addressStoresfix.csv");
        for (CSVRecord record : records) {
            System.out.println(record);
        }
    }

    private void createClientData(){

    }

    private void createStoreData(){

    }

    private void createDiscountData(){

    }

    private void createProductData(){

    }

    private void createCartData(){

    }

    private void createOrderData(){

    }

    private void createShipmentData(){

    }

    private void createTransactionData(){

    }
}
