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
public class NeoClient implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    @OneToOne
    @JoinColumn(name = "addressId",referencedColumnName = "id")
    private SqlAddress address;

   /* protected NeoClient(){}

    public NeoClient(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }*/
}
