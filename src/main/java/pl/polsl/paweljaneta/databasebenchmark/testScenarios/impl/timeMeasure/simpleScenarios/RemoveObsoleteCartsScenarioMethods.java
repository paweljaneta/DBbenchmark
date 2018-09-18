package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.simpleScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoCart;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoCartRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoCart;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoCartRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlCart;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlCartRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class RemoveObsoleteCartsScenarioMethods{
    @Autowired
    private SqlCartRepository sqlCartRepository;
    @Autowired
    private MongoCartRepository mongoCartRepository;
    @Autowired
    private NeoCartRepository neoCartRepository;

    @ExecTimeMeasure
    public List<SqlCart> getAllSqlCarts() {
        return sqlCartRepository.findAll();
    }

    @ExecTimeMeasure
    public void removeSqlCarts(List<SqlCart> carts) {
        sqlCartRepository.deleteAll(carts);
    }

    @ExecTimeMeasure
    public List<MongoCart> getAllMongoCarts() {
        return mongoCartRepository.findAll();
    }

    @ExecTimeMeasure
    public void removeMongoCarts(List<MongoCart> carts) {
        mongoCartRepository.deleteAll(carts);
    }

    @ExecTimeMeasure
    public List<NeoCart> getAllNeoCarts() {
        List<NeoCart> result = new ArrayList<>();
        for (NeoCart neoCart : neoCartRepository.findAll()) {
            result.add(neoCart);
        }
        return result;
    }

    @ExecTimeMeasure
    public void removeNeoCarts(List<NeoCart> carts) {
        neoCartRepository.deleteAll(carts);
    }
}
