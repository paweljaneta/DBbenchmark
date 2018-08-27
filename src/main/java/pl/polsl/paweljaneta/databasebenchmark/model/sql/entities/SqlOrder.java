package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "ORDER", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class SqlOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long orderId;
    @ManyToMany
    @JoinTable(name = "ORDER_PRODUCT", joinColumns = {
            @JoinColumn(name = "orderId", referencedColumnName = "orderId")},
            inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"))
    private List<SqlProduct> products;
    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    private SqlClient client;
    private Long entityId;
}
