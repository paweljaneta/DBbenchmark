package pl.polsl.paweljaneta.databasebenchmark.dataInsertion;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.DataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl.MongoDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl.NeoDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl.SqlDataInsertor;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;
import java.util.stream.IntStream;

@Component
public class DataCreator {
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
    private List<MongoCart> mongoStoreCarts = new ArrayList<>();
    private List<MongoClient> mongoClients = new ArrayList<>();
    private List<MongoDiscount> mongoDiscounts = new ArrayList<>();
    private List<MongoOrder> mongoOrders = new ArrayList<>();
    private List<MongoProduct> mongoProducts = new ArrayList<>();
    private List<MongoShipment> mongoShipments = new ArrayList<>();
    private List<MongoStore> mongoStores = new ArrayList<>();
    private List<MongoTransaction> mongoTransactions = new ArrayList<>();

    private Map<DatabaseToInsert,List<DataInsertor>> databasesToInsert;
    private DatabaseToInsert databaseToInsert = DatabaseToInsert.ALL;

    @Autowired
    private SqlDataInsertor sqlDataInsertor;

    @Autowired
    public DataCreator(SqlDataInsertor sqlDataInsertor, MongoDataInsertor mongoDataInsertor, NeoDataInsertor neoDataInsertor){
        /*databasesToInsert.put(DatabaseToInsert.ALL, Arrays.asList(sqlDataInsertor,mongoDataInsertor,neoDataInsertor));
        databasesToInsert.put(DatabaseToInsert.MONGO,Arrays.asList(mongoDataInsertor));
        databasesToInsert.put(DatabaseToInsert.NEO4J,Arrays.asList(neoDataInsertor));
        databasesToInsert.put(DatabaseToInsert.SQL,Arrays.asList(sqlDataInsertor));*/
    }

    @EventListener(ApplicationReadyEvent.class)
    public void createData() {
        createStoreAddressData();
        createClientAddressData();
        createClientData();
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

    private List<Integer> fillListWithIndexes(int endIndex){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=0;i<=endIndex;i++){
            result.add(i);
        }
        return result;
    }

    private void createClientAddressData() {
        Iterable<CSVRecord> records = loadCSV("addressClients.csv");
        sqlDataInsertor.insertClientAddressData(sqlClientAddresses,records);
    }

    private void createStoreAddressData() {
        Iterable<CSVRecord> records = loadCSV("addressStores.csv");
        sqlDataInsertor.insertClientAddressData(sqlStoreAddresses,records);
    }

    private void createClientData() {
        Iterable<CSVRecord> records = loadCSV("clients.csv");
        List<Integer> addressIndexes = fillListWithIndexes(sqlClientAddresses.size()-1);
        Collections.shuffle(addressIndexes);

        sqlDataInsertor.insertClientData(sqlClients,records,sqlClientAddresses,addressIndexes);
    }

    private void createStoreData() {
        Iterable<CSVRecord> records = loadCSV("stores.csv");

    }

    private void createDiscountData() {
        Iterable<CSVRecord> records = loadCSV("discounts.csv");

    }

    private void createProductData() {
        Iterable<CSVRecord> records = loadCSV("products.csv");

    }

    private void createCartData() {

    }

    private void createOrderData() {

    }

    private void createShipmentData() {
        Iterable<CSVRecord> records = loadCSV("shipments.csv");

    }

    private void createTransactionData() {
        Iterable<CSVRecord> records = loadCSV("transactions.csv");

    }


    enum DatabaseToInsert{
        ALL,SQL,MONGO,NEO4J
    }
}
