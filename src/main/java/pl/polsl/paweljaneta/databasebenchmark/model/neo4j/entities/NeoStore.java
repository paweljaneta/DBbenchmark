package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlAddress;

import javax.persistence.*;
import java.io.Serializable;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoStore implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private SqlAddress address;

//    private List<NeoProduct> products;
}
