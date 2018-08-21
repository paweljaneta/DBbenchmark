package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.*;
import java.io.Serializable;

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
}
