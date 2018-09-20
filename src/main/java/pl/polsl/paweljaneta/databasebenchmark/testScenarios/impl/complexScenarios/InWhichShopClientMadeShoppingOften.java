package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class InWhichShopClientMadeShoppingOften extends BaseScenario {
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlClientRepository sqlClientRepository;

    @Override
    public void before() {

    }

    @Override
    public void execute() {

    }

    @Override
    public void after() {

    }

    public List<SqlClient> getSqlClients() {
        return sqlClientRepository.findAll();
    }

    public List<SqlTransaction> getSqlTransactionsForClient(SqlClient client) {
        return sqlTransactionRepository.findAllByClientId(client.getId());
    }

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

    public SqlStore getSqlStoreForClient(Map<SqlStore, Integer> storeCount) {
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

}
