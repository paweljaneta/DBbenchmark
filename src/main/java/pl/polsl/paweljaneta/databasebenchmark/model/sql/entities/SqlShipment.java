package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "SHIPMENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlShipment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "orderId",referencedColumnName = "orderId")
    private SqlOrder order;
    private String tracingNumber;
    private String shipmentDetails;
    private Long entityId;
}
