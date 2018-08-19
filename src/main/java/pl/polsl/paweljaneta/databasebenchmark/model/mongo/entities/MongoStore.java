package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlAddress;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoStore implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private SqlAddress address;

//    private List<NeoProduct> products;
}
