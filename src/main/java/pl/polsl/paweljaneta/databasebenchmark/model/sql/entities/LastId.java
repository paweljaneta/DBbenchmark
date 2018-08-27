package pl.polsl.paweljaneta.databasebenchmark.model.sql.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public", name = "LAST_ID")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LastId {

    @Id
    @GeneratedValue
    private Long id;
    private Long lastId;
}
