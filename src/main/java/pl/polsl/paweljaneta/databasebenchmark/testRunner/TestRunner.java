package pl.polsl.paweljaneta.databasebenchmark.testRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
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
                      RemoveObsoleteCartsScenario removeObsoleteCartsScenario) {

        loadScenario.setNoOfRepeats(1);
        scenarios.add(loadScenario); //działa

       /*addProductToStoreScenario.setNoOfRepeats(100);
        scenarios.add(addProductToStoreScenario); //działa

        anonymousBuyScenario.setNoOfRepeats(100);
        scenarios.add(anonymousBuyScenario);  //działa spróbować zoptymalizować pobieranie produktów mongo

        clientAddressEditScenario.setNoOfRepeats(100);
        scenarios.add(clientAddressEditScenario);  //działa

        clientDataEditScenario.setNoOfRepeats(100);
        scenarios.add(clientDataEditScenario); //działa

        getProductsScenario.setNoOfRepeats(100);
        scenarios.add(getProductsScenario);  //działa

        loggedBuyScenario.setNoOfRepeats(100);
        scenarios.add(loggedBuyScenario); //działa + jak w anonimowym

        removeObsoleteCartsScenario.setNoOfRepeats(100);
        scenarios.add(removeObsoleteCartsScenario); //działa*/
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
