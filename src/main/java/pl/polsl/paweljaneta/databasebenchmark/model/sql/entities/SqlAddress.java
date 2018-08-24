package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "ADDRESS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;
}
