package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NodeEntity(label = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoProduct implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    private NeoDiscount discount;
    private Long entityId;

/*    @Relationship(type = "CARTS_IN_PRODUCT", direction = Relationship.UNDIRECTED)
    private Set<NeoCart> carts;
    @Relationship(type = "ORDERS_IN_PRODUCT", direction = Relationship.UNDIRECTED)
    private Set<NeoOrder> orders;
    @Relationship(type = "TRANSACTIONS_IN_PRODUCT", direction = Relationship.OUTGOING)
    private Set<NeoTransaction> transactions;*/
}
