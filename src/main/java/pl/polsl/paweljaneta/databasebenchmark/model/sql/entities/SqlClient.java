package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "public", name = "CLIENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SqlClient implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    @OneToOne
    @JoinColumn(name = "addressId",referencedColumnName = "id")
    private SqlAddress address;

   /* protected NeoClient(){}

    public NeoClient(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }*/
}
