package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NodeEntity(label = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoAddress implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;
    private Long entityId;
}
