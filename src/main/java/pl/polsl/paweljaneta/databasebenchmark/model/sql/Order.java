package pl.polsl.paweljaneta.databasebenchmark.model.sql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
//    @OneToMany
//    @JoinColumn
//    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    private Client client;
}
