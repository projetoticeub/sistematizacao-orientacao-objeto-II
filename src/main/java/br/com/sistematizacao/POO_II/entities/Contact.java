package br.com.sistematizacao.POO_II.entities;

import br.com.sistematizacao.POO_II.dtos.ContactSaveDTO;
import br.com.sistematizacao.POO_II.dtos.ContactUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(ContactSaveDTO c) {
        this.name = c.name();
        this.phoneNumber = c.phoneNumber();
        this.email = c.email();
    }

    public void update(ContactUpdateDTO c) {
        if (c.name() != null) {
            this.name = c.name();
        }
        if (c.phoneNumber() != null) {
            this.phoneNumber = c.phoneNumber();
        }
        if (c.email() != null) {
            this.email = c.email();
        }
    }
}
