package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.dto.AddressDTO;
import pl.polsl.paweljaneta.databasebenchmark.dto.DiscountDTO;
import pl.polsl.paweljaneta.databasebenchmark.dto.ProductDTO;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.*;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductsInStoresRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.GetProductsScenarioMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GetProductsScenario extends BaseScenario {

    private DataConfig dataConfig;
    private GetProductsScenarioMethods productsScenarioMethods;


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

        System.out.println(sqlAddressFromStore.getStreetNumber() + " " + mongoAddressFromStore.getStreetNumber() + " " + neoAddressFromStore.getStreetNumber());
        System.out.println(sqlProductsFromStore.size() + " " + mongoProductsFromStore.size() + " " + neoProductsFromStore.size());
    }

    @Override
    public void after() {

    }

    private Long getStoreEntityId() {
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }


}
