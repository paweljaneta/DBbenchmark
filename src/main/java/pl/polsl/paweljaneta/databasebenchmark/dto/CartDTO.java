package pl.polsl.paweljaneta.databasebenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO implements Serializable {

    private ClientDTO client;
    private List<ProductDTO> products;
    private Long entityId;
}
