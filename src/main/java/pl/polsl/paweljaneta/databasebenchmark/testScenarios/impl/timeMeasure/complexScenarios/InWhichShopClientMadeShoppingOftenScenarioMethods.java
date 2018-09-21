package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class InWhichShopClientMadeShoppingOftenScenarioMethods {
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlClientRepository sqlClientRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoClientRepository mongoClientRepository;
    @Autowired
    private MongoStoreRepository mongoStoreRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;
    @Autowired
    private NeoClientRepository neoClientRepository;

    @ExecTimeMeasure
    public List<SqlClient> getSqlClients() {
        return sqlClientRepository.findAll();
    }

    private List<SqlTransaction> getSqlTransactionsForClient(SqlClient client) {
        return sqlTransactionRepository.findAllByClientId(client.getId());
    }

    @ExecTimeMeasure
    public Map<SqlClient, SqlStore> getMostPopularSqlStoreForClient(List<SqlClient> clients) {
        Map<SqlClient, SqlStore> result = new HashMap<>();
        for (SqlClient client : clients) {
            List<SqlTransaction> transactionsForClient = getSqlTransactionsForClient(client);
            Map<SqlStore, Integer> mapStoreCount = new HashMap<>();
            for (SqlTransaction transaction : transactionsForClient) {
                if (mapStoreCount.containsKey(transaction.getStore())) {
                    Integer integer = mapStoreCount.get(transaction.getStore());
                    integer++;
                    mapStoreCount.remove(transaction.getStore());
                    mapStoreCount.put(transaction.getStore(), integer);
                } else {
                    mapStoreCount.put(transaction.getStore(), 1);
                }
            }
            result.put(client, getSqlStoreForClient(mapStoreCount));
        }
        return result;
    }

    private SqlStore getSqlStoreForClient(Map<SqlStore, Integer> storeCount) {
        Set<SqlStore> keySet = storeCount.keySet();
        Integer maxValue = 0;
        SqlStore store = null;
        for (SqlStore sqlStore : keySet) {
            if (storeCount.get(sqlStore) > maxValue) {
                maxValue = storeCount.get(sqlStore);
                store = sqlStore;
            }
        }
        return store;
    }


    @ExecTimeMeasure
    public List<MongoClient> getMongoClients() {
        return mongoClientRepository.findAll();
    }

    private List<MongoTransaction> getMongoTransactionsForClient(MongoClient client) {
        return mongoTransactionRepository.findAllByClientId(client.getId());
    }

    @ExecTimeMeasure
    public Map<MongoClient, MongoStore> getMostPopularMongoStoreForClient(List<MongoClient> clients) {
        Map<MongoClient, MongoStore> result = new HashMap<>();
        for (MongoClient client : clients) {
            List<MongoTransaction> transactionsForClient = getMongoTransactionsForClient(client);
            Map<String, Integer> mapStoreCount = new HashMap<>();
            for (MongoTransaction transaction : transactionsForClient) {
                if (mapStoreCount.containsKey(transaction.getStoreId())) {
                    Integer integer = mapStoreCount.get(transaction.getStoreId());
                    integer++;
                    mapStoreCount.remove(transaction.getStoreId());
                    mapStoreCount.put(transaction.getStoreId(), integer);
                } else {
                    mapStoreCount.put(transaction.getStoreId(), 1);
                }
            }
            result.put(client, getMongoStoreForClient(mapStoreCount));
        }
        return result;
    }

    private MongoStore getMongoStoreForClient(Map<String, Integer> storeCount) {
        Set<String> keySet = storeCount.keySet();
        Integer maxValue = 0;
        String storeId = null;
        for (String mongoStoreId : keySet) {
            if (storeCount.get(mongoStoreId) > maxValue) {
                maxValue = storeCount.get(mongoStoreId);
                storeId = mongoStoreId;
            }
        }
        if (storeId != null) {
            return mongoStoreRepository.findById(storeId).get();
        } else {
            return null;
        }
    }

    @ExecTimeMeasure
    public Iterable<NeoClient> getNeoClients() {
        return neoClientRepository.findAll();
    }

    private List<NeoTransaction> getNeoTransactionsForClient(NeoClient client) {
        return neoTransactionRepository.findAllByClientId(client.getId());
    }

    @ExecTimeMeasure
    public Map<NeoClient, NeoStore> getMostPopularNeoStoreForClient(Iterable<NeoClient> clients) {
        Map<NeoClient, NeoStore> result = new HashMap<>();
        for (NeoClient client : clients) {
            List<NeoTransaction> transactionsForClient = getNeoTransactionsForClient(client);
            Map<NeoStore, Integer> mapStoreCount = new HashMap<>();
            for (NeoTransaction transaction : transactionsForClient) {
                if (mapStoreCount.containsKey(transaction.getStore())) {
                    Integer integer = mapStoreCount.get(transaction.getStore());
                    integer++;
                    mapStoreCount.remove(transaction.getStore());
                    mapStoreCount.put(transaction.getStore(), integer);
                } else {
                    mapStoreCount.put(transaction.getStore(), 1);
                }
            }
            result.put(client, getNeoStoreForClient(mapStoreCount));
        }
        return result;
    }

    private NeoStore getNeoStoreForClient(Map<NeoStore, Integer> storeCount) {
        Set<NeoStore> keySet = storeCount.keySet();
        Integer maxValue = 0;
        NeoStore store = null;
        for (NeoStore neoStore : keySet) {
            if (storeCount.get(neoStore) > maxValue) {
                maxValue = storeCount.get(neoStore);
                store = neoStore;
            }
        }
        return store;
    }

}
