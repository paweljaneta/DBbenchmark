package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.dto.AddressDTO;
import pl.polsl.paweljaneta.databasebenchmark.dto.ProductDTO;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.simpleScenarios.GetProductsScenarioMethods;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class GetProductsScenario extends BaseScenario {

    private DataConfig dataConfig;
    private GetProductsScenarioMethods productsScenarioMethods;
    Logger logger = Logger.getLogger(this.getClass().getName());


    @Autowired
    public GetProductsScenario(DataConfig dataConfig, GetProductsScenarioMethods productsScenarioMethods){
        this.dataConfig=dataConfig;
        this.productsScenarioMethods=productsScenarioMethods;
    }

    @Override
    public void before() {

    }

    @Override
    @ExecTimeMeasure
    public void execute() {
        Long storeEntityId = getStoreEntityId();

        SqlStore sqlStore = productsScenarioMethods.getSqlStoreByEntityId(storeEntityId);
        MongoStore mongoStore = productsScenarioMethods.getMongoStoreByEntityId(storeEntityId);
        NeoStore neoStore = productsScenarioMethods.getNeoStoreByEntityId(storeEntityId);

        AddressDTO sqlAddressFromStore = productsScenarioMethods.getSqlAddressFromStore(sqlStore);
        AddressDTO mongoAddressFromStore = productsScenarioMethods.getMongoAddressFromStore(mongoStore);
        AddressDTO neoAddressFromStore = productsScenarioMethods.getNeoAddressFromStore(neoStore);

        List<ProductDTO> sqlProductsFromStore = productsScenarioMethods.getSqlProductsFromStore(sqlStore);
        List<ProductDTO> mongoProductsFromStore = productsScenarioMethods.getMongoProductsFromStore(mongoStore);
        List<ProductDTO> neoProductsFromStore = productsScenarioMethods.getNeoProductsFromStore(neoStore);

        logger.log(Level.INFO, sqlAddressFromStore.getStreetNumber() + " " + mongoAddressFromStore.getStreetNumber() + " " + neoAddressFromStore.getStreetNumber());
        logger.log(Level.INFO, sqlProductsFromStore.size() + " " + mongoProductsFromStore.size() + " " + neoProductsFromStore.size());
    }

    @Override
    public void after() {

    }

    private Long getStoreEntityId() {
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }


}
