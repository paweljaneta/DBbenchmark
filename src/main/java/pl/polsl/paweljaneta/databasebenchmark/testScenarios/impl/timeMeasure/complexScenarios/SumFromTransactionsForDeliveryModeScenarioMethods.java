package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SumFromTransactionsForDeliveryModeScenarioMethods {
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;

    @ExecTimeMeasure
    public Map<DeliveryMode, Float> sqlGetTransactionsSumForDeliveryMode() {
        Map<DeliveryMode, Float> result = new HashMap<>();
        for (DeliveryMode deliveryMode : DeliveryMode.values()) {
            List<SqlTransaction> sqlTransactionsByDeliveryMode = sqlTransactionRepository.findAllByDeliveryMode(deliveryMode);
            float sum = 0f;
            for (SqlTransaction transaction : sqlTransactionsByDeliveryMode) {
                List<SqlProduct> products = sqlProductRepository.findAllByTransactionsIn(Collections.singletonList(transaction));
                for (SqlProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
            result.put(deliveryMode, sum);
        }
        return result;
    }

    @ExecTimeMeasure
    public Map<DeliveryMode, Float> mongoGetTransactionsSumForDeliveryMode() {
        Map<DeliveryMode, Float> result = new HashMap<>();
        for (DeliveryMode deliveryMode : DeliveryMode.values()) {
            List<MongoTransaction> mongoTransactionsByDeliveryMode = mongoTransactionRepository.findAllByDeliveryMode(deliveryMode);
            float sum = 0f;
            for (MongoTransaction transaction : mongoTransactionsByDeliveryMode) {
                List<MongoProduct> products = transaction.getProducts();
                for (MongoProduct product : products) {
                    sum += product.getPrice() * mongoDiscountRepository.findById(product.getDiscountId()).get().getDiscountValue() / 100.0f;
                }
            }
            result.put(deliveryMode, sum);
        }
        return result;
    }

    @ExecTimeMeasure
    public Map<DeliveryMode, Float> neoGetTransactionsSumForDeliveryMode() {
        Map<DeliveryMode, Float> result = new HashMap<>();
        for (DeliveryMode deliveryMode : DeliveryMode.values()) {
            List<NeoTransaction> neoTransactionsByDeliveryMode = neoTransactionRepository.findAllByDeliveryMode(deliveryMode);
            float sum = 0f;
            for (NeoTransaction transaction : neoTransactionsByDeliveryMode) {
                List<NeoProduct> products = transaction.getProducts();
                for (NeoProduct product : products) {
                    sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
                }
            }
            result.put(deliveryMode, sum);
        }
        return result;
    }
}
