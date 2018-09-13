package pl.polsl.paweljaneta.databasebenchmark.testRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestRunner {

    private List<BaseScenario> scenarios = new ArrayList<>();

    /*@Autowired
    TestRunner(LoadScenario loadScenario, GetProductsScenario getProductsScenario){
       *//* loadScenario.setNoOfRepeats(1);
        scenarios.add(loadScenario);*//*
        getProductsScenario.setNoOfRepeats(1);
        scenarios.add(getProductsScenario);

    }*/

    @Autowired
    public TestRunner(AddProductToStoreScenario addProductToStoreScenario, AnonymousBuyScenario anonymousBuyScenario,
                      ClientAddressEditScenario clientAddressEditScenario, ClientDataEditScenario clientDataEditScenario,
                      GetProductsScenario getProductsScenario, LoadScenario loadScenario, LoggedBuyScenario loggedBuyScenario,
                      RemoveObsoleteCartsScenario removeObsoleteCartsScenario) {

       /*addProductToStoreScenario.setNoOfRepeats(1);
        scenarios.add(addProductToStoreScenario);*/ //działa

      /*  anonymousBuyScenario.setNoOfRepeats(1);
        scenarios.add(anonymousBuyScenario);*/  //działa spróbować zoptymalizować pobieranie produktów mongo

       /* clientAddressEditScenario.setNoOfRepeats(1);
        scenarios.add(clientAddressEditScenario); */ //działa

       /* clientDataEditScenario.setNoOfRepeats(1);
        scenarios.add(clientDataEditScenario);*/ //działa

       /* getProductsScenario.setNoOfRepeats(1);
        scenarios.add(getProductsScenario);*/  //działa

       /* loadScenario.setNoOfRepeats(1);
        scenarios.add(loadScenario);*/ //działa

       /* loggedBuyScenario.setNoOfRepeats(1);
        scenarios.add(loggedBuyScenario);*/ //działa + jak w anonimowym

        /*removeObsoleteCartsScenario.setNoOfRepeats(1);
        scenarios.add(removeObsoleteCartsScenario);*/ //działa
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runTests() {
        for (BaseScenario scenario : scenarios) {
            scenario.executeScenario();
        }
    }
}
