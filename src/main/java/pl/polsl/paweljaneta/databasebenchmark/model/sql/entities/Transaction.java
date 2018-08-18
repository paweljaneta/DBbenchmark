package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Store store;
    @OneToMany
    @JoinColumn(name = "productId", referencedColumnName = "id")
    private List<Product> products;
    private DeliveryMode deliveryMode;
}
