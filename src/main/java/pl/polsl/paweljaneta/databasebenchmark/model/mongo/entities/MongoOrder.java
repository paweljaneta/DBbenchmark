package pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoOrder implements Serializable {

    @Id
    private String id;
    private List<MongoProduct> products;
    private MongoClient client;
}
