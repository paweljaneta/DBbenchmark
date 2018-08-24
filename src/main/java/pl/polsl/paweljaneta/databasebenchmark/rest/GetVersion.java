package pl.polsl.paweljaneta.databasebenchmark.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoAddress;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoProduct;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoTransaction;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoAddressRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/rest")
public class GetVersion {

    @Autowired
    private NeoAddressRepository addressRepository;
    @Autowired
    private NeoClientRepository clientRepository;
    @Autowired
    private NeoProductRepository productRepository;
    @Autowired
    private NeoTransactionRepository transactionRepository;


    private void setClient(){
        NeoAddress address = new NeoAddress();
        address.setCity("Gliwice");
        address.setPostalCode("44-100");
        address.setStreet("Pszczynska");
        address.setStreetNumber("8");
        addressRepository.save(address);
        NeoClient client = new NeoClient();
        client.setAddress(address);
        client.setEmail("test@test.pl");
        client.setName("Andzej");
        client.setPhoneNumber("555155547");
        clientRepository.save(client);
    }

    private void getClient(){
        Iterable<NeoAddress> addresses = addressRepository.findAll();
        Iterable<NeoClient> clients = clientRepository.findAll();
    }

    private void setProductsAndTransactions(){
        NeoProduct product1= new NeoProduct();
        product1.setName("test1");
        product1.setPrice(2.0f);
        productRepository.save(product1);
        NeoProduct product2= new NeoProduct();
        product2.setName("test2");
        product2.setPrice(4.0f);
        productRepository.save(product2);
        NeoProduct product3= new NeoProduct();
        product3.setName("test3");
        product3.setPrice(6.0f);
        productRepository.save(product3);

        NeoTransaction transaction1 = new NeoTransaction();
        transaction1.setDeliveryMode(DeliveryMode.PICKUP_IN_STORE);
        transaction1.setProducts(Arrays.asList(product1,product3));
        transactionRepository.save(transaction1);
        NeoTransaction transaction2 = new NeoTransaction();
        transaction2.setDeliveryMode(DeliveryMode.SHIPMENT);
        transaction2.setProducts(Arrays.asList(product1,product2));
        transactionRepository.save(transaction2);

        Set<NeoTransaction> transactions = new HashSet<NeoTransaction>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        product1.setTransactions(transactions);
        productRepository.save(product1);
        transactions.clear();
        transactions.add(transaction1);
        product3.setTransactions(transactions);
        productRepository.save(product3);
        transactions.clear();
        transactions.add(transaction2);
        product2.setTransactions(transactions);
        productRepository.save(product2);
    }

    private void getProductsAndTransactions(){
        Iterable<NeoProduct> products = productRepository.findAll();
        /*List<NeoTransaction> NeoTransactions = products.get(0).getTransactions();
        for (NeoTransaction NeoTransaction : NeoTransactions) {
            System.out.println(NeoTransaction.getTransactionId().toString());
        }*/
        Iterable<NeoTransaction> transactions = transactionRepository.findAll();
        /*List<NeoProduct> NeoProducts = transactions.get(0).getProducts();
        for (NeoProduct NeoProduct : NeoProducts) {
            System.out.println(NeoProduct.getId());
        }*/
    }

    private void updateProduct(){
       /* NeoProduct product = productRepository.findById("5b7dacdc3ba66a2214c2d9f0").get();
        product.setName("updated");
        productRepository.save(product);*/
    }

    @GetMapping(path = "/set")
    public String set(){
       // setClient();
        setProductsAndTransactions();
        Long count = addressRepository.count();
        count = addressRepository.count();
        return "<html></html>";
    }

    @GetMapping(path = "/get")
    public String get(){
        //getClient();
        getProductsAndTransactions();
        Long count = addressRepository.count();
        count = addressRepository.count();
        return "<html></html>";
    }

    @GetMapping(path = "/update")
    public String update(){
        updateProduct();
        return "<html></html>";
    }
}
