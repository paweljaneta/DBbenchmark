package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
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
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SumFromTransactionForStoreScenarioMethods {
    @Autowired
    private SqlStoreRepository sqlStoreRepository;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;

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

    @ExecTimeMeasure
    public List<SqlStore> sqlGetAllStores() {
        return sqlStoreRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<SqlStore, Float> sqlCalculateTransactionSumForStore(List<SqlStore> stores) {
        Map<SqlStore, Float> result = new HashMap<>();
        for (SqlStore store : stores) {
            float sum = 0.0f;
            List<SqlTransaction> transactionsByStoreId = sqlTransactionRepository.findAllByStoreId(store.getId());
            for (SqlTransaction transaction : transactionsByStoreId) {
                List<SqlProduct> products = sqlProductRepository.findAllByTransactionsIn(Collections.singletonList(transaction));
                for (SqlProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
            result.put(store, sum);
        }
        return result;
    }

    @ExecTimeMeasure
    public List<MongoStore> mongoGetAllStores() {
        return mongoStoreRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<MongoStore, Float> mongoCalculateTransactionSumForStore(List<MongoStore> stores) {
        Map<MongoStore, Float> result = new HashMap<>();
        for (MongoStore store : stores) {
            float sum = 0.0f;
            List<MongoTransaction> transactionsByStoreId = mongoTransactionRepository.findAllByStoreId(store.getId());
            for (MongoTransaction transaction : transactionsByStoreId) {
                List<MongoProduct> products = transaction.getProducts();
                for (MongoProduct product : products) {
                    sum += product.getPrice() * mongoDiscountRepository.findById(product.getDiscountId()).get().getDiscountValue() / 100.0f;

                }
            }
            result.put(store, sum);
        }
        return result;
    }

    @ExecTimeMeasure
    public Iterable<NeoStore> neoGetAllStores() {
        return neoStoreRepository.findAll();
    }

    @ExecTimeMeasure
    public Map<NeoStore, Float> neoCalculateTransactionSumForStore(Iterable<NeoStore> stores) {
        Map<NeoStore, Float> result = new HashMap<>();
        for (NeoStore store : stores) {
            float sum = 0.0f;
            List<NeoTransaction> transactionsByStoreId = neoTransactionRepository.findAllByStoreId(store.getId());
            for (NeoTransaction transaction : transactionsByStoreId) {
                List<NeoProduct> products = transaction.getProducts();
                for (NeoProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
            result.put(store, sum);
        }
        return result;
    }
}
