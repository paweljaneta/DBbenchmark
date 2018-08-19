package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoAddress implements Serializable {

    @Id
    private String id;
    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;
}
