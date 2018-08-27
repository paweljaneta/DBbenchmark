package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(schema = "public", name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class SqlProduct implements Serializable {

    @Id
    @GeneratedValue
    private Long productId;
    private String name;
    private float price;
    @ManyToOne
    @JoinColumn(name = "discountId",referencedColumnName = "id")
    private SqlDiscount discount;

    @ManyToMany(mappedBy = "products")
    private List<SqlTransaction> transactions;

    @ManyToMany(mappedBy = "products")
    private List<SqlCart> carts;
    @ManyToMany(mappedBy = "products")
    private List<SqlOrder> orders;
    private Long entityId;
}
