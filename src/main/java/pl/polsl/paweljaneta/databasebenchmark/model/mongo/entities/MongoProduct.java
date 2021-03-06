package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoProduct implements Serializable {

    @Id
    @GeneratedValue
    private String id;
    private String name;
    private float price;
    private String discountId;
    private Long entityId;
}
