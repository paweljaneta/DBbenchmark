package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoTransaction implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private MongoStore store;
    @OneToMany
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private List<MongoProduct> products;
    private DeliveryMode deliveryMode;
}
