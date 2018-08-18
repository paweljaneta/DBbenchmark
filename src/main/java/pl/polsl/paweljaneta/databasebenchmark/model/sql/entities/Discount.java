package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private float discountValue;
    @OneToMany(mappedBy = "discount")
    private Set<Product> products;

   /* protected Discount() {}

    public Discount(float discountValue, Product product) {
        this.discountValue = discountValue;
        this.product = product;
    }*/
}
