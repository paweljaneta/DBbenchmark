package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Document(collection = "discount")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoDiscount implements Serializable {

    @Id
    private String id;
    private float discountValue;
   /* @DBRef
    private Set<MongoProduct> products;*/
    private Long entityId;
}
