package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "PRODUCTS_IN_STORES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SqlProductsInStoresPK.class)
public class SqlProductsInStores implements Serializable {

    @Id
    private Long storeId;
    @Id
    private Long productId;
    private Long quantity;
}
