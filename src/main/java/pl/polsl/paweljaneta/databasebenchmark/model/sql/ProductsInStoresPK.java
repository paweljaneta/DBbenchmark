package pl.polsl.paweljaneta.databasebenchmark.model.sql;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
@Data
public class ProductsInStoresPK implements Serializable {
    @Id
    private Long storeId;
    @Id
    private Long productId;
}
