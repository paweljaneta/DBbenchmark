package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoProduct implements Serializable {

    @Id
    @GeneratedValue
    private String id;
    private String name;
    private float price;
    private MongoDiscount discount;
}
