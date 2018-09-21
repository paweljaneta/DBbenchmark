package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;

import java.util.*;

@Component
public class CashSpentForClientScenarioMethods {
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlClientRepository sqlClientRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoClientRepository mongoClientRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;
    @Autowired
    private NeoClientRepository neoClientRepository;


    @ExecTimeMeasure
    public List<SqlClient> getSqlClients() {
        return sqlClientRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<SqlClient, Float> getSqlClientPays(List<SqlClient> clients) {
        Map<SqlClient, Float> result = new HashMap<>();
        for (SqlClient client : clients) {
            List<SqlTransaction> transactions = sqlTransactionRepository.findAllByClientId(client.getId());
            List<SqlProduct> products = new ArrayList<>();
            for (SqlTransaction transaction : transactions) {
                products.addAll(sqlProductRepository.findAllByTransactionsIn(Collections.singletonList(transaction)));
            }
            result.put(client, sumValueOfSqlProducts(products));
        }
        return result;
    }

    private Float sumValueOfSqlProducts(List<SqlProduct> products) {
        Float sum = 0f;
        for (SqlProduct product : products) {
            sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
        }
        return sum;
    }

    @ExecTimeMeasure
    public List<MongoClient> getMongoClients() {
        return mongoClientRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<MongoClient, Float> getMongoClientPays(List<MongoClient> clients) {
        Map<MongoClient, Float> result = new HashMap<>();
        for (MongoClient client : clients) {
            List<MongoTransaction> transactions = mongoTransactionRepository.findAllByClientId(client.getId());
            List<MongoProduct> products = new ArrayList<>();
            for (MongoTransaction transaction : transactions) {
                products.addAll(transaction.getProducts());
            }
            result.put(client, sumValueOfMongoProducts(products));
        }
        return result;
    }

    private Float sumValueOfMongoProducts(List<MongoProduct> products) {
        Float sum = 0f;
        for (MongoProduct product : products) {
            sum += product.getPrice() * mongoDiscountRepository.findById(product.getDiscountId()).get().getDiscountValue() / 100.0f;
        }
        return sum;
    }


    @ExecTimeMeasure
    public Iterable<NeoClient> getNeoClients() {
        return neoClientRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<NeoClient, Float> getNeoClientPays(Iterable<NeoClient> clients) {
        Map<NeoClient, Float> result = new HashMap<>();
        for (NeoClient client : clients) {
            List<NeoTransaction> transactions = neoTransactionRepository.findAllByClientId(client.getId());
            List<NeoProduct> products = new ArrayList<>();
            for (NeoTransaction transaction : transactions) {
                products.addAll(transaction.getProducts());
            }
            result.put(client, sumValueOfNeoProducts(products));
        }
        return result;
    }

    private Float sumValueOfNeoProducts(List<NeoProduct> products) {
        Float sum = 0f;
        for (NeoProduct product : products) {
            sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
        }
        return sum;
    }
}
