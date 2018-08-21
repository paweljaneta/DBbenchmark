package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.io.Serializable;

@Document(collection = "shipment")
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
