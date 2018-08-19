package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoShipment implements Serializable {
    @Id
    private String id;
    private MongoOrder order;
    private String tracingNumber;
    private String shipmentDetails;
}
