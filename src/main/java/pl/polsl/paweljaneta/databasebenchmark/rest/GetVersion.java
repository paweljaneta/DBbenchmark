package pl.polsl.paweljaneta.databasebenchmark.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.Address;
import pl.polsl.paweljaneta.databasebenchmark.repository.AddressRepository;

@Controller
@RequestMapping("/rest")
public class GetVersion {

    private final AddressRepository addressRepository;

    @Autowired
    public GetVersion(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping(path = "/version")
    public String getVersion(){
        System.setProperty("dupa", "0");
        Long count = addressRepository.count();
        System.setProperty("dupa", "1");
        count = addressRepository.count();
        return count.toString();
    }
}
