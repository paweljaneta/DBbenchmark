package pl.polsl.paweljaneta.databasebenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDTO implements Serializable {

    private float discountValue;
    private Set<ProductDTO> products;
    private Long entityId;
}
