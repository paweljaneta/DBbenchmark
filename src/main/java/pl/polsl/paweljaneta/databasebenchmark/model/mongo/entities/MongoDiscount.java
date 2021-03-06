package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoDiscount implements Serializable {

    @Id
    private String id;
    private float discountValue;
    private Long entityId;
}
