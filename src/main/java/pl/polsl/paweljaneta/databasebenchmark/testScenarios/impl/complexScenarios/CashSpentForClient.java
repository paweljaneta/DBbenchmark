package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;

import java.util.*;

public class CashSpentForClient extends BaseScenario {
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlClientRepository sqlClientRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;

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

    public Map<SqlClient, Float> getClientPays(List<SqlClient> clients) {
        Map<SqlClient, Float> result = new HashMap<>();
        for (SqlClient client : clients) {
            List<SqlTransaction> transactions = sqlTransactionRepository.findAllByClientId(client.getId());
            List<SqlProduct> products = new ArrayList<>();
            for (SqlTransaction transaction : transactions) {
                products.addAll(sqlProductRepository.findAllByTransactionsIn(Collections.singletonList(transaction)));
            }
            result.put(client, sumValueOfSqlProducts(products));
        }
        return result;
    }

    public Float sumValueOfSqlProducts(List<SqlProduct> products) {
        Float sum = 0f;
        for (SqlProduct product : products) {
            sum += product.getPrice() * product.getDiscount().getDiscountValue() / 100.0f;
        }
        return sum;
    }
}
