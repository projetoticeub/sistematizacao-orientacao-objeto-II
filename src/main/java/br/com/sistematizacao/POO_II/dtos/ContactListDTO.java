package br.com.sistematizacao.POO_II.dtos;

import br.com.sistematizacao.POO_II.entities.Contact;

public record ContactListDTO(Long id,
                             String name,
                             String phoneNumber,
                             String email) {

    public ContactListDTO(Contact contact){
        this(contact.getId(),
                contact.getName(),
                contact.getPhoneNumber(),
                contact.getEmail());
    }

}
