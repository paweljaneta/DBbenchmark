package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Relationship(type="HAS")
    private NeoStore store;
    @Relationship(type = "HAS")
    private NeoClient client;
    private List<NeoProduct> products;
    private DeliveryMode deliveryMode;
    private Date date;
    private Long entityId;
}
