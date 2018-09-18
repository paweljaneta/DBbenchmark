package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "productsInStores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoProductsInStores implements Serializable {

    @Id
    private String id;
    private String storeId;
    private String productId;
    private Long quantity;
    private Long entityId;
}
