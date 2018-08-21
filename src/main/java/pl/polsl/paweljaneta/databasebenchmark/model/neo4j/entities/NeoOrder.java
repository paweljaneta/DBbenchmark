package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NodeEntity(label = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Relationship(type = "PRODUCTS_IN_ORDER", direction = Relationship.UNDIRECTED)
    private List<NeoProduct> products;
    private NeoClient client;
}
