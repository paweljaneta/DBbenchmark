package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoCart;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoCart;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlCart;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.simpleScenarios.RemoveObsoleteCartsScenarioMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class RemoveObsoleteCartsScenario extends BaseScenario {
    private DataConfig dataConfig;
    private RemoveObsoleteCartsScenarioMethods removeObsoleteCartsScenarioMethods;
    private final int NO_OF_CARTS_TO_DELETE = 10;

    @Autowired
    public RemoveObsoleteCartsScenario(DataConfig dataConfig, RemoveObsoleteCartsScenarioMethods removeObsoleteCartsScenarioMethods) {
        this.dataConfig = dataConfig;
        this.removeObsoleteCartsScenarioMethods = removeObsoleteCartsScenarioMethods;
    }

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        List<SqlCart> sqlCarts = removeObsoleteCartsScenarioMethods.getAllSqlCarts();
        List<MongoCart> mongoCarts = removeObsoleteCartsScenarioMethods.getAllMongoCarts();
        List<NeoCart> neoCarts = removeObsoleteCartsScenarioMethods.getAllNeoCarts();

        List<Integer> indexesToDelete = indexesToDelete(sqlCarts.size());
        List<SqlCart> sqlCartsToDelete = getSqlCartsToDelete(indexesToDelete, sqlCarts);
        List<MongoCart> mongoCartsToDelete = getMongoCartsToDelete(indexesToDelete, mongoCarts);
        List<NeoCart> neoCartsToDelete = getNeoCartsToDelete(indexesToDelete, neoCarts);

        removeObsoleteCartsScenarioMethods.removeSqlCarts(sqlCartsToDelete);
        removeObsoleteCartsScenarioMethods.removeMongoCarts(mongoCartsToDelete);
        removeObsoleteCartsScenarioMethods.removeNeoCarts(neoCartsToDelete);
    }

    @Override
    public void after() {

    }

    private List<Integer> indexesToDelete(int size) {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < NO_OF_CARTS_TO_DELETE; i++) {
            result.add(random.nextInt(size));
        }
        return result;
    }

    private List<SqlCart> getSqlCartsToDelete(List<Integer> indexes, List<SqlCart> carts) {
        List<SqlCart> result = new ArrayList<>();
        for (Integer index : indexes) {
            result.add(carts.get(index));
        }
        return result;
    }

    private List<MongoCart> getMongoCartsToDelete(List<Integer> indexes, List<MongoCart> carts) {
        List<MongoCart> result = new ArrayList<>();
        for (Integer index : indexes) {
            result.add(carts.get(index));
        }
        return result;
    }

    private List<NeoCart> getNeoCartsToDelete(List<Integer> indexes, List<NeoCart> carts) {
        List<NeoCart> result = new ArrayList<>();
        for (Integer index : indexes) {
            result.add(carts.get(index));
        }
        return result;
    }
}
