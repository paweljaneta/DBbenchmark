package pl.polsl.paweljaneta.databasebenchmark.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO implements Serializable {

    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;
    private Long entityId;
}
