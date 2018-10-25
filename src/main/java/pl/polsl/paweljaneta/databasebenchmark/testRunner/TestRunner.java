package pl.polsl.paweljaneta.databasebenchmark.testRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.exceptionHandler.ExceptionHandler;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios.*;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TestRunner {
    @Autowired
    private ExceptionHandler exceptionHandler;

    private List<BaseScenario> scenarios = new ArrayList<>();
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    public TestRunner(AddProductToStoreScenario addProductToStoreScenario, AnonymousBuyScenario anonymousBuyScenario,
                      ClientAddressEditScenario clientAddressEditScenario, ClientDataEditScenario clientDataEditScenario,
                      GetProductsScenario getProductsScenario, LoadScenario loadScenario, LoggedBuyScenario loggedBuyScenario,
                      RemoveObsoleteCartsScenario removeObsoleteCartsScenario,
                      SumFromTransactionForStoreScenario sumFromTransactionForStoreScenario, SumFromTransactionsByStoreCityScenario sumFromTransactionsByStoreCityScenario,
                      SumFromTransactionsForClientCityScenario sumFromTransactionsForClientCityScenario, SumFromTransactionsForDeliveryModeScenario sumFromTransactionsForDeliveryModeScenario,
                      CashSpentForClientScenario cashSpentForClientScenario, InWhichShopClientMadeShoppingOftenScenario inWhichShopClientMadeShoppingOftenScenario) {

        loadScenario.setNoOfRepeats(1);
        scenarios.add(loadScenario);

        addProductToStoreScenario.setNoOfRepeats(100);
        scenarios.add(addProductToStoreScenario);


        anonymousBuyScenario.setNoOfRepeats(100);
        scenarios.add(anonymousBuyScenario);


        clientAddressEditScenario.setNoOfRepeats(100);
        scenarios.add(clientAddressEditScenario);


        clientDataEditScenario.setNoOfRepeats(100);
        scenarios.add(clientDataEditScenario);


        getProductsScenario.setNoOfRepeats(100);
        scenarios.add(getProductsScenario);


        loggedBuyScenario.setNoOfRepeats(100);
        scenarios.add(loggedBuyScenario);


        removeObsoleteCartsScenario.setNoOfRepeats(100);
        scenarios.add(removeObsoleteCartsScenario);

        //complex

        sumFromTransactionForStoreScenario.setNoOfRepeats(100);
        scenarios.add(sumFromTransactionForStoreScenario);


        sumFromTransactionsByStoreCityScenario.setNoOfRepeats(100);
        scenarios.add(sumFromTransactionsByStoreCityScenario);


        sumFromTransactionsForClientCityScenario.setNoOfRepeats(100);
        scenarios.add(sumFromTransactionsForClientCityScenario);


        sumFromTransactionsForDeliveryModeScenario.setNoOfRepeats(100);
        scenarios.add(sumFromTransactionsForDeliveryModeScenario);


        cashSpentForClientScenario.setNoOfRepeats(100);
        scenarios.add(cashSpentForClientScenario);


        inWhichShopClientMadeShoppingOftenScenario.setNoOfRepeats(100);
        scenarios.add(inWhichShopClientMadeShoppingOftenScenario);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runTests() {
        logger.log(Level.INFO, "Tests started");
        for (BaseScenario scenario : scenarios) {
            scenario.executeScenario();
        }
        logger.log(Level.INFO, "Tests finished");

        if (exceptionHandler.getExceptions().size() > 0) {
            logger.log(Level.INFO, "Exceptions:");
            logger.log(Level.INFO, exceptionHandler.getExceptions().toString());
        }
    }
}
