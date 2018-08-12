package pl.polsl.paweljaneta.databasebenchmark.model.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    @Id
    @GeneratedValue
    private Long id;
    private float discountValue;
    private Product product;

   /* protected Discount() {}

    public Discount(float discountValue, Product product) {
        this.discountValue = discountValue;
        this.product = product;
    }*/
}
