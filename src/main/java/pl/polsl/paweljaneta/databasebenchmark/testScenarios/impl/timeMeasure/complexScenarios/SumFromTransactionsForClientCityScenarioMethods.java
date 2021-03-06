package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoAddressRepository;
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
public class SumFromTransactionsForClientCityScenarioMethods {
    @Autowired
    private SqlClientRepository sqlClientRepository;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;

    @Autowired
    private MongoClientRepository mongoClientRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoAddressRepository mongoAddressRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;

    @Autowired
    private NeoClientRepository neoClientRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;

    @ExecTimeMeasure
    public List<SqlClient> getAllSqlClients() {
        return sqlClientRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<String, List<SqlClient>> sqlGetAllClientCities(List<SqlClient> clients) {
        Map<String, List<SqlClient>> result = new HashMap<>();
        for (SqlClient client : clients) {
            String city = client.getAddress().getCity();
            if (result.containsKey(city)) {
                result.get(city).add(client);
            } else {
                List<SqlClient> clientList = new ArrayList<>();
                clientList.add(client);
                result.put(city, clientList);
            }
        }
        return result;
    }

    private Float sqlCalculateTransactionSumForClients(List<SqlClient> clients) {
        float sum = 0.0f;
        for (SqlClient client : clients) {
            List<SqlTransaction> transactionsByStoreId = sqlTransactionRepository.findAllByClientId(client.getId());
            for (SqlTransaction transaction : transactionsByStoreId) {
                List<SqlProduct> products = sqlProductRepository.findAllByTransactionsIn(Collections.singletonList(transaction));
                for (SqlProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
        }
        return sum;
    }

    @ExecTimeMeasure
    public Map<String, Float> sqlCalculateSumForCity(Map<String, List<SqlClient>> cityClientsMap) {
        Map<String, Float> result = new HashMap<>();
        Set<String> keys = cityClientsMap.keySet();
        for (String key : keys) {
            result.put(key, sqlCalculateTransactionSumForClients(cityClientsMap.get(key)));
        }
        return result;
    }

    @ExecTimeMeasure
    public List<MongoClient> getAllMongoClients() {
        return mongoClientRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<String, List<MongoClient>> mongoGetAllClientCities(List<MongoClient> clients) {
        Map<String, List<MongoClient>> result = new HashMap<>();
        for (MongoClient client : clients) {
            String city = mongoAddressRepository.findById(client.getAddressId()).get().getCity();

            if (result.containsKey(city)) {
                result.get(city).add(client);
            } else {
                List<MongoClient> clientList = new ArrayList<>();
                clientList.add(client);
                result.put(city, clientList);
            }
        }
        return result;
    }

    private Float mongoCalculateTransactionSumForClients(List<MongoClient> clients) {
        float sum = 0.0f;
        for (MongoClient client : clients) {
            List<MongoTransaction> transactionsByStoreId = mongoTransactionRepository.findAllByClientId(client.getId());
            for (MongoTransaction transaction : transactionsByStoreId) {
                List<MongoProduct> products = transaction.getProducts();
                for (MongoProduct product : products) {
                    sum += product.getPrice() * mongoDiscountRepository.findById(product.getDiscountId()).get().getDiscountValue() / 100.0f;
                }
            }
        }
        return sum;
    }

    @ExecTimeMeasure
    public Map<String, Float> mongoCalculateSumForCity(Map<String, List<MongoClient>> cityClientsMap) {
        Map<String, Float> result = new HashMap<>();
        Set<String> keys = cityClientsMap.keySet();
        for (String key : keys) {
            result.put(key, mongoCalculateTransactionSumForClients(cityClientsMap.get(key)));
        }
        return result;
    }

    @ExecTimeMeasure
    public Iterable<NeoClient> getAllNeoClients() {
        return neoClientRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<String, List<NeoClient>> neoGetAllClientCities(Iterable<NeoClient> clients) {
        Map<String, List<NeoClient>> result = new HashMap<>();
        for (NeoClient client : clients) {
            String city = client.getAddress().getCity();
            if (result.containsKey(city)) {
                result.get(city).add(client);
            } else {
                List<NeoClient> clientList = new ArrayList<>();
                clientList.add(client);
                result.put(city, clientList);
            }
        }
        return result;
    }

    private Float neoCalculateTransactionSumForClients(List<NeoClient> clients) {
        float sum = 0.0f;
        for (NeoClient client : clients) {
            List<NeoTransaction> transactionsByStoreId = neoTransactionRepository.findAllByClientId(client.getId());
            for (NeoTransaction transaction : transactionsByStoreId) {
                List<NeoProduct> products = transaction.getProducts();
                for (NeoProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
        }
        return sum;
    }

    @ExecTimeMeasure
    public Map<String, Float> neoCalculateSumForCity(Map<String, List<NeoClient>> cityClientsMap) {
        Map<String, Float> result = new HashMap<>();
        Set<String> keys = cityClientsMap.keySet();
        for (String key : keys) {
            result.put(key, neoCalculateTransactionSumForClients(cityClientsMap.get(key)));
        }
        return result;
    }
}
