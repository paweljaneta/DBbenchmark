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
public class Shipment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long orderId;
    @OneToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    private Order order;
    private String tracingNumber;
    private String shipmentDetails;
}
