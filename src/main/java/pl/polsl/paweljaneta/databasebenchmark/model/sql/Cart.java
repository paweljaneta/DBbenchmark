package pl.polsl.paweljaneta.databasebenchmark.model.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Client client;

    @ManyToOne
    private List<Product> products;

   /* protected Cart(){}

    public Cart(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }*/
}
