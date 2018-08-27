package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "public", name = "TRANSACTION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlTransaction implements Serializable {
    @Id
    @GeneratedValue
    private Long transactionId;
    private SqlStore store;
    @ManyToMany
    @JoinTable(name = "TRANSACTION_PRODUCT", joinColumns = {
            @JoinColumn(name = "transactionId", referencedColumnName = "transactionId")},
            inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"))
    private List<SqlProduct> products;
    private DeliveryMode deliveryMode;
    private Date date;
    private Long entityId;
}
