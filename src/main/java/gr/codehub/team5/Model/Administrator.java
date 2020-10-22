package gr.codehub.team5.Model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Administrator")

public class Administrator {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    public Administrator(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
