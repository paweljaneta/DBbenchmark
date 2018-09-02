package pl.polsl.paweljaneta.databasebenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO implements Serializable {

    private StoreDTO store;
    private List<ProductDTO> products;
    private DeliveryMode deliveryMode;
    private Date date;
    private Long entityId;
}
