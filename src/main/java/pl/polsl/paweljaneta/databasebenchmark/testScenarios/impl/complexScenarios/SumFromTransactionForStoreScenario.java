package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionForStoreScenarioMethods;

import java.util.List;
import java.util.Map;

@Component
public class SumFromTransactionForStoreScenario extends BaseScenario {
    @Autowired
    private SumFromTransactionForStoreScenarioMethods methods;
    @Override
    public void before() {

    }

    @Override
    public void execute() {
        List<SqlStore> sqlStores = methods.sqlGetAllStores();
        Map<SqlStore, Float> sqlStorePriceMap = methods.sqlCalculateTransactionSumForStore(sqlStores);

        List<MongoStore> mongoStores = methods.mongoGetAllStores();
        Map<MongoStore, Float> mongoStorePriceMap = methods.mongoCalculateTransactionSumForStore(mongoStores);

        Iterable<NeoStore> neoStores = methods.neoGetAllStores();
        Map<NeoStore, Float> neoStorePriceMap = methods.neoCalculateTransactionSumForStore(neoStores);

        System.out.println(sqlStorePriceMap);
        System.out.println(mongoStorePriceMap);
        System.out.println(neoStorePriceMap);
    }

    @Override
    public void after() {

    }
}
