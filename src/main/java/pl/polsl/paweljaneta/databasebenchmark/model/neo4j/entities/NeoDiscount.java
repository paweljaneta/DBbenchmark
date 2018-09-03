package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NodeEntity(label = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoDiscount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private float discountValue;
   /* @Relationship(type = "DISCOUNTS_IN_PRODUCTS", direction = Relationship.UNDIRECTED)
    private Set<NeoProduct> products;*/
    private Long entityId;
}
