package pl.polsl.paweljaneta.databasebenchmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    static ApplicationContext applicationContext;

    public static void main(String[] args) {
        System.setProperty("dupa", "0");
        SpringApplication.run(DemoApplication.class, args);
    }

}
