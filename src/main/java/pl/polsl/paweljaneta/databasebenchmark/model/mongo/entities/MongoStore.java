package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlAddress;

import javax.persistence.*;
import java.io.Serializable;

@Document(collection = "store")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoStore implements Serializable {
    @Id
    private String id;
    private String name;
    private MongoAddress address;
}
