package pl.polsl.paweljaneta.databasebenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    private String name;
    private float price;
    private DiscountDTO discount;
    private Long entityId;
}
