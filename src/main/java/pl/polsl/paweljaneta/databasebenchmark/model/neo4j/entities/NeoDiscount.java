package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NodeEntity(label = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoDiscount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private float discountValue;
    private Long entityId;
}
