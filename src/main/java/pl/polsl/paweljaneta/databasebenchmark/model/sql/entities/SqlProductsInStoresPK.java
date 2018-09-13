package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
@Data
public class SqlProductsInStoresPK implements Serializable {
    @Id
    private Long storeId;
    @Id
    private Long productId;
}
