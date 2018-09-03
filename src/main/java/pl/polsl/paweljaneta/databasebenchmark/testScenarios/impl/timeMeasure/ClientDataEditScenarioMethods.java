package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;

@Component
public class ClientDataEditScenarioMethods {
    @Autowired
    private SqlClientRepository sqlClientRepository;

    @Autowired
    private MongoClientRepository mongoClientRepository;

    @Autowired
    private NeoClientRepository neoClientRepository;

    @ExecTimeMeasure
    public void changeSqlClientData(Long entityId){
        SqlClient sqlClient = findSqlClient(entityId);
        editSqlClientData(sqlClient);
        saveSqlClientData(sqlClient);
    }

    private SqlClient findSqlClient(Long entityId){
        return sqlClientRepository.findByEntityId(entityId);
    }

    private void editSqlClientData(SqlClient client){
        client.setName("Andrzej");
        client.setEmail("test@test.pl");
        client.setPhoneNumber("555-555-555");
    }

    private void saveSqlClientData(SqlClient sqlClient){
        sqlClientRepository.save(sqlClient);
    }

    @ExecTimeMeasure
    public void changeMongoClientData(Long entityId){
        MongoClient mongoClient = findMongoClient(entityId);
        editMongoClientData(mongoClient);
        saveMongoClientData(mongoClient);
    }

    private MongoClient findMongoClient(Long entityId){
        return mongoClientRepository.findByEntityId(entityId);
    }

    private void editMongoClientData(MongoClient client){
        client.setName("Andrzej");
        client.setEmail("test@test.pl");
        client.setPhoneNumber("555-555-555");
    }

    private void saveMongoClientData(MongoClient mongoClient){
        mongoClientRepository.save(mongoClient);
    }

    @ExecTimeMeasure
    public void changeNeoClientData(Long entityId){
        NeoClient neoClient = findNeoClient(entityId);
        editNeoClientData(neoClient);
        saveNeoClientData(neoClient);
    }

    private NeoClient findNeoClient(Long entityId){
        return neoClientRepository.findByEntityId(entityId);
    }

    private void editNeoClientData(NeoClient client){
        client.setName("Andrzej");
        client.setEmail("test@test.pl");
        client.setPhoneNumber("555-555-555");
    }

    private void saveNeoClientData(NeoClient neoClient){
        neoClientRepository.save(neoClient);
    }
}
