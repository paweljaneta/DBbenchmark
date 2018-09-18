package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;

import java.util.*;

public class SumFromTransactionsByStoreCity extends BaseScenario {
    @Autowired
    private SqlStoreRepository sqlStoreRepository;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private MongoStoreRepository mongoStoreRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;
    @Autowired
    private NeoStoreRepository neoStoreRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;

    @Override
    public void before() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void after() {

    }

    public List<SqlStore> sqlGetAllStores() {
        return sqlStoreRepository.findAll();
    }

    public Map<String, List<SqlStore>> sqlGetAllStoreCities(List<SqlStore> stores) {
        Map<String, List<SqlStore>> result = new HashMap<>();
        for (SqlStore store : stores) {
            String city = store.getAddress().getCity();
            if (result.containsKey(city)) {
                result.get(city).add(store);
            } else {
                List<SqlStore> storeList = new ArrayList<>();
                storeList.add(store);
                result.put(city, storeList);
            }
        }
        return result;
    }

    public Float sqlCalculateTransactionSumForStores(List<SqlStore> stores) {
        float sum = 0.0f;
        for (SqlStore store : stores) {
            List<SqlTransaction> transactionsByStoreId = sqlTransactionRepository.findAllByStoreId(store.getId());
            for (SqlTransaction transaction : transactionsByStoreId) {
                List<SqlProduct> products = transaction.getProducts();
                for (SqlProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
        }
        return sum;
    }

    public Map<String, Float> sqlCalculateSumForCity(Map<String, List<SqlStore>> cityStoresMap) {
        Map<String, Float> result = new HashMap<>();
        Set<String> keys = cityStoresMap.keySet();
        for (String key : keys) {
            result.put(key, sqlCalculateTransactionSumForStores(cityStoresMap.get(key)));
        }
        return result;
    }

    public List<MongoStore> mongoGetAllStores() {
        return mongoStoreRepository.findAll();
    }

    public Map<String, List<MongoStore>> mongoGetAllStoreCities(List<MongoStore> stores) {
        Map<String, List<MongoStore>> result = new HashMap<>();
        for (MongoStore store : stores) {
            String city = store.getAddress().getCity();
            if (result.containsKey(city)) {
                result.get(city).add(store);
            } else {
                List<MongoStore> storeList = new ArrayList<>();
                storeList.add(store);
                result.put(city, storeList);
            }
        }
        return result;
    }

    public Float mongoCalculateTransactionSumForStores(List<MongoStore> stores) {
        float sum = 0.0f;
        for (MongoStore store : stores) {
            List<MongoTransaction> transactionsByStoreId = mongoTransactionRepository.findAllByStoreId(store.getId());
            for (MongoTransaction transaction : transactionsByStoreId) {
                List<MongoProduct> products = transaction.getProducts();
                for (MongoProduct product : products) {
                    sum += product.getPrice() * mongoDiscountRepository.findById(product.getDiscountId()).get().getDiscountValue() / 100.0f;
                }
            }
        }
        return sum;
    }

    public Map<String, Float> mongoCalculateSumForCity(Map<String, List<MongoStore>> cityStoresMap) {
        Map<String, Float> result = new HashMap<>();
        Set<String> keys = cityStoresMap.keySet();
        for (String key : keys) {
            result.put(key, mongoCalculateTransactionSumForStores(cityStoresMap.get(key)));
        }
        return result;
    }

    public List<NeoStore> neoGetAllStores() {
        List<NeoStore> result = new ArrayList<>();
        Iterable<NeoStore> stores = neoStoreRepository.findAll();
        for (NeoStore store : stores) {
            result.add(store);
        }
        return result;
    }

    public Map<String, List<NeoStore>> neoGetAllStoreCities(List<NeoStore> stores) {
        Map<String, List<NeoStore>> result = new HashMap<>();
        for (NeoStore store : stores) {
            String city = store.getAddress().getCity();
            if (result.containsKey(city)) {
                result.get(city).add(store);
            } else {
                List<NeoStore> storeList = new ArrayList<>();
                storeList.add(store);
                result.put(city, storeList);
            }
        }
        return result;
    }

    public Float neoCalculateTransactionSumForStores(List<NeoStore> stores) {
        float sum = 0.0f;
        for (NeoStore store : stores) {
            List<NeoTransaction> transactionsByStoreId = neoTransactionRepository.findAllByStoreId(store.getId());
            for (NeoTransaction transaction : transactionsByStoreId) {
                List<NeoProduct> products = transaction.getProducts();
                for (NeoProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
        }
        return sum;
    }

    public Map<String, Float> neoCalculateSumForCity(Map<String, List<NeoStore>> cityStoresMap) {
        Map<String, Float> result = new HashMap<>();
        Set<String> keys = cityStoresMap.keySet();
        for (String key : keys) {
            result.put(key, neoCalculateTransactionSumForStores(cityStoresMap.get(key)));
        }
        return result;
    }

}
