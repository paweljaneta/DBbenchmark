package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(schema = "public", name = "DISCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlDiscount implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private float discountValue;
    @OneToMany(mappedBy = "discount")
    private Set<SqlProduct> products;

}
