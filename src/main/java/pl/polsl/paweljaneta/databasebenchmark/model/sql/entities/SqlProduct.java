package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(schema = "public", name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class SqlProduct implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    @ManyToOne
    @JoinColumn(name = "discountId",referencedColumnName = "id")
    private SqlDiscount discount;
}
