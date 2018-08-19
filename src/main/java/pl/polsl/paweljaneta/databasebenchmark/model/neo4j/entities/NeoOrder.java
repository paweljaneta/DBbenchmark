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
public class NeoOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
//    @OneToMany
//    @JoinColumn
//    private List<NeoProduct> products;
    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    private NeoClient client;
}
