package pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeoProductsInStores implements Serializable {

    @Id
    private Long storeId;
    @Id
    private Long productId;
    private Long quantity;
}
