package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoDiscount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private float discountValue;
    @OneToMany(mappedBy = "discount")
    private Set<NeoProduct> products;

   /* protected NeoDiscount() {}

    public NeoDiscount(float discountValue, NeoProduct product) {
        this.discountValue = discountValue;
        this.product = product;
    }*/
}
