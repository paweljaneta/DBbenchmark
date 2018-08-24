package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NodeEntity(label = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoTransaction implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private NeoStore store;
    @Relationship(type = "PRODUCTS_IN_TRANSACTIONS", direction = Relationship.OUTGOING)
    private List<NeoProduct> products;
    private DeliveryMode deliveryMode;
    private Date date;
}
