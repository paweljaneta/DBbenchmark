package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NodeEntity(label = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoCart implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private NeoClient client;
    //@Relationship(type = "PRODUCTS_IN_CART", direction = Relationship.UNDIRECTED)
    private List<NeoProduct> products;
    private Long entityId;
}
