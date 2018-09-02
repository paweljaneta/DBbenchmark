package pl.polsl.paweljaneta.databasebenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentDTO implements Serializable {

    private OrderDTO order;
    private String tracingNumber;
    private String shipmentDetails;
    private Long entityId;
}
