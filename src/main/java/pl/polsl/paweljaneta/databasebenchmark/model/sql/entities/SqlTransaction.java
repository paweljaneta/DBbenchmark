package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(schema = "public", name = "TRANSACTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlTransaction implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private SqlStore store;
    @OneToMany
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private List<SqlProduct> products;
    private DeliveryMode deliveryMode;
}
