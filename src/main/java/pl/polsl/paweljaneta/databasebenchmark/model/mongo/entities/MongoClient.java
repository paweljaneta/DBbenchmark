package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoClient implements Serializable {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
    private String addressId;
    private Long entityId;
}
