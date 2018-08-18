package pl.polsl.paweljaneta.databasebenchmark.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.Address;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.AddressRepository;

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
        Address address = new Address();
        address.setPostalCode("111-11");
        address.setCity("Sosnowjec");
        address.setStreet("wiejska");
        addressRepository.save(address);
        Long count = addressRepository.count();
        count = addressRepository.count();
        return count.toString();
    }
}
