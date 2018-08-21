package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.*;
import java.io.Serializable;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoShipment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private NeoOrder order;
    private String tracingNumber;
    private String shipmentDetails;
}
