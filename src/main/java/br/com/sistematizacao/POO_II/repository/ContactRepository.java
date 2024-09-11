package br.com.sistematizacao.POO_II.repository;

import br.com.sistematizacao.POO_II.dtos.ContactListDTO;
import br.com.sistematizacao.POO_II.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact deleteByName(String name);
    Contact deleteByPhoneNumber(String phoneNumber);

    Page<ContactListDTO> findAll(Specification<Contact> spec, Pageable pageable);

}
