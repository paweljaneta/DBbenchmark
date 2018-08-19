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
public class MongoClient implements Serializable {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private MongoAddress address;
}
