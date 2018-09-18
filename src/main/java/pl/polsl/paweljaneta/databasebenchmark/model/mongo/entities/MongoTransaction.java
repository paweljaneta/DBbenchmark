package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Document(collection = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoTransaction implements Serializable {
    @Id
    private String id;
    private String storeId;
    private String clientId;
    private List<MongoProduct> products;
    private DeliveryMode deliveryMode;
    private Date date;
    private Long entityId;
}
