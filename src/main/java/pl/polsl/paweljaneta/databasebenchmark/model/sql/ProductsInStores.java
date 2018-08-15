package pl.polsl.paweljaneta.databasebenchmark.model.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ProductsInStoresPK.class)
public class ProductsInStores implements Serializable {

    private Long storeId;
    private Long productId;
    private Long quantity;
}
