package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoAddress;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoAddressRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoAddress;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlAddress;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlAddressRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;

@Component
public class ClientAddressEditScenarioMethods {
    @Autowired
    private SqlClientRepository sqlClientRepository;

    @Autowired
    private MongoClientRepository mongoClientRepository;

    @Autowired
    private NeoClientRepository neoClientRepository;

    @Autowired
    private SqlAddressRepository sqlAddressRepository;

    @Autowired
    private MongoAddressRepository mongoAddressRepository;

    @ExecTimeMeasure
    public void changeSqlClientAddressData(Long entityId) {
        SqlClient sqlClient = findSqlClient(entityId);
        SqlAddress sqlAddress = findSqlAddress(sqlClient);
        editSqlAddressData(sqlAddress);
        sqlAddressRepository.save(sqlAddress);
    }

    private SqlClient findSqlClient(Long entityId) {
        return sqlClientRepository.findByEntityId(entityId);
    }

    private SqlAddress findSqlAddress(SqlClient client) {
        return client.getAddress();
    }

    private void editSqlAddressData(SqlAddress address) {
        address.setStreetNumber("8");
        address.setStreet("Testowa");
        address.setPostalCode("12-345");
        address.setCity("Gliwice");
    }

    private void saveSqlClientData(SqlClient sqlClient) {
        sqlClientRepository.save(sqlClient);
    }

    @ExecTimeMeasure
    public void changeMongoClientAddressData(Long entityId) {
        MongoClient mongoClient = findMongoClient(entityId);
        MongoAddress mongoAddress = findMongoAddress(mongoClient);
        editMongoAddressData(mongoAddress);
        mongoAddressRepository.save(mongoAddress);
    }

    private MongoClient findMongoClient(Long entityId) {
        return mongoClientRepository.findByEntityId(entityId);
    }

    private MongoAddress findMongoAddress(MongoClient client) {
        return mongoAddressRepository.findById(client.getAddressId()).get();
    }

    private void editMongoAddressData(MongoAddress address) {
        address.setStreetNumber("8");
        address.setStreet("Testowa");
        address.setPostalCode("12-345");
        address.setCity("Gliwice");
    }

    private void saveMongoClientData(MongoClient mongoClient) {
        mongoClientRepository.save(mongoClient);
    }

    @ExecTimeMeasure
    public void changeNeoClientAddressData(Long entityId) {
        NeoClient neoClient = findNeoClient(entityId);
        NeoAddress neoAddress = findNeoAddress(neoClient);
        editNeoAddressData(neoAddress);
        saveNeoClientData(neoClient);
    }

    private NeoClient findNeoClient(Long entityId) {
        return neoClientRepository.findByEntityId(entityId);
    }

    private NeoAddress findNeoAddress(NeoClient client) {
        return client.getAddress();
    }

    private void editNeoAddressData(NeoAddress address) {
        address.setStreetNumber("8");
        address.setStreet("Testowa");
        address.setPostalCode("12-345");
        address.setCity("Gliwice");
    }

    private void saveNeoClientData(NeoClient neoClient) {
        neoClientRepository.save(neoClient);
    }
}
