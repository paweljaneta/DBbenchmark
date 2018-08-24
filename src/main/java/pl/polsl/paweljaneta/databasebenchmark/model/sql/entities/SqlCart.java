package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(schema = "public", name = "CART")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    private SqlClient client;

    @ManyToMany
    @JoinTable(name = "CART_PRODUCT", joinColumns = {
            @JoinColumn(name = "cartId", referencedColumnName = "cartId")},
            inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"))
   private List<SqlProduct> products;
}
