package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.ClientDataEditScenarioMethods;

import java.util.Random;

@Component
public class ClientDataEditScenario extends BaseScenario {
    @Autowired
    private ClientDataEditScenarioMethods clientDataEditScenarioMethods;
    @Autowired
    private DataConfig dataConfig;

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        Random random = new Random();
        Long entityId = Long.valueOf(random.nextInt(dataConfig.NO_OF_CLIENTS));
        clientDataEditScenarioMethods.changeSqlClientData(entityId);
        clientDataEditScenarioMethods.changeMongoClientData(entityId);
        clientDataEditScenarioMethods.changeNeoClientData(entityId);
    }

    @Override
    public void after() {

    }
    

}
