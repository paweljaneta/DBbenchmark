package pl.polsl.paweljaneta.databasebenchmark.model.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private float price;
    private Long discountId;
    @ManyToOne
    @JoinColumn(name = "discountId",referencedColumnName = "id")
    private Discount discount;
}
