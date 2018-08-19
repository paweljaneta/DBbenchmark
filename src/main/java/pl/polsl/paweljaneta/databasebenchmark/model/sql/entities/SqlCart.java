package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "CART")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private SqlClient client;

//    private List<NeoProduct> products;

   /* protected NeoCart(){}

    public NeoCart(NeoClient client, List<NeoProduct> products) {
        this.client = client;
        this.products = products;
    }*/
}
