package pl.polsl.paweljaneta.databasebenchmark.testRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios.SumFromTransactionForStoreScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios.SumFromTransactionsByStoreCityScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios.SumFromTransactionsForClientCity;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios.SumFromTransactionsForDeliveryMode;
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
                      SumFromTransactionsForClientCity sumFromTransactionsForClientCity, SumFromTransactionsForDeliveryMode sumFromTransactionsForDeliveryMode) {

        /*loadScenario.setNoOfRepeats(1);
        scenarios.add(loadScenario); //działa

        addProductToStoreScenario.setNoOfRepeats(1);
        scenarios.add(addProductToStoreScenario); //działa

        anonymousBuyScenario.setNoOfRepeats(1);
        scenarios.add(anonymousBuyScenario);  //działa spróbować zoptymalizować pobieranie produktów mongo

        clientAddressEditScenario.setNoOfRepeats(1);
        scenarios.add(clientAddressEditScenario);  //działa

        clientDataEditScenario.setNoOfRepeats(1);
        scenarios.add(clientDataEditScenario); //działa

        getProductsScenario.setNoOfRepeats(1);
        scenarios.add(getProductsScenario);  //działa

        loggedBuyScenario.setNoOfRepeats(1);
        scenarios.add(loggedBuyScenario); //działa + jak w anonimowym

        removeObsoleteCartsScenario.setNoOfRepeats(1);
        scenarios.add(removeObsoleteCartsScenario); //działa*/



       /* sumFromTransactionForStoreScenario.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionForStoreScenario);*/ //działa

    /*    sumFromTransactionsByStoreCityScenario.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionsByStoreCityScenario); */ //działa

       /* sumFromTransactionsForClientCity.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionsForClientCity);*/  //działa

        sumFromTransactionsForDeliveryMode.setNoOfRepeats(1);
        scenarios.add(sumFromTransactionsForDeliveryMode);
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
