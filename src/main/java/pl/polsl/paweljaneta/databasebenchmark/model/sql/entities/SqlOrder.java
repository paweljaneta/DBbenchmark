package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "ORDER", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class SqlOrder implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
//    @OneToMany
//    @JoinColumn
//    private List<NeoProduct> products;
    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    private SqlClient client;
}
