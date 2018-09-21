package pl.polsl.paweljaneta.databasebenchmark.testRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios.*;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestRunner {

    private List<BaseScenario> scenarios = new ArrayList<>();

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

        addProductToStoreScenario.setNoOfRepeats(1);
        scenarios.add(addProductToStoreScenario);

        anonymousBuyScenario.setNoOfRepeats(1);
        scenarios.add(anonymousBuyScenario);

        clientAddressEditScenario.setNoOfRepeats(1);
        scenarios.add(clientAddressEditScenario);

        clientDataEditScenario.setNoOfRepeats(1);
        scenarios.add(clientDataEditScenario);

        getProductsScenario.setNoOfRepeats(1);
        scenarios.add(getProductsScenario);

        loggedBuyScenario.setNoOfRepeats(1);
        scenarios.add(loggedBuyScenario);

        removeObsoleteCartsScenario.setNoOfRepeats(1);
        scenarios.add(removeObsoleteCartsScenario);


        sumFromTransactionForStoreScenario.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionForStoreScenario);

        sumFromTransactionsByStoreCityScenario.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionsByStoreCityScenario);

        sumFromTransactionsForClientCityScenario.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionsForClientCityScenario);

        sumFromTransactionsForDeliveryModeScenario.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionsForDeliveryModeScenario);

        cashSpentForClientScenario.setNoOfRepeats(1);
        scenarios.add(cashSpentForClientScenario);

        inWhichShopClientMadeShoppingOftenScenario.setNoOfRepeats(1);
        scenarios.add(inWhichShopClientMadeShoppingOftenScenario);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runTests() {
        for (BaseScenario scenario : scenarios) {
            System.out.println("Scenario: "+scenario.getClass().getCanonicalName()+" start");
            scenario.executeScenario();
            System.out.println("Scenario: "+scenario.getClass().getCanonicalName()+" finish");
        }
        System.out.println("Tests finished");
    }
}
