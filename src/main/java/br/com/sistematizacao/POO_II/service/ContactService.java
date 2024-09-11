package br.com.sistematizacao.POO_II.service;

import br.com.sistematizacao.POO_II.dtos.ContactListDTO;
import br.com.sistematizacao.POO_II.dtos.ContactSaveDTO;
import br.com.sistematizacao.POO_II.dtos.ContactUpdateDTO;
import br.com.sistematizacao.POO_II.entities.Contact;
import br.com.sistematizacao.POO_II.exceptions.GlobalException;
import br.com.sistematizacao.POO_II.repository.ContactRepository;
import br.com.sistematizacao.POO_II.specification.ContactSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Transactional
    public Contact saveContact(ContactSaveDTO contactDTO) {
        var contact = new Contact(contactDTO);
        return repository.save(contact);
    }

    public Contact findContactById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GlobalException("ID not found" + id));
    }

    public Page<Contact> listAllContactByPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<ContactListDTO> listByParam(String name, String phoneNumber, Pageable pageable) {
        if(name != null && !name.trim().isEmpty()) {
            name = name.replace("-"," ");
        }
        Specification<Contact> spec = ContactSpecification.param(name, phoneNumber);
        Page<ContactListDTO> contacts = repository.findAll(spec, pageable);
        if(contacts.isEmpty()) {
            throw new GlobalException("No contacts found with the given parameters.");
        }
        return contacts;
    }

    @Transactional
    public Contact updateContact(ContactUpdateDTO contactDTO, Long id) {
        Optional<Contact> contactOpt = repository.findById(id);
        if(contactOpt.isEmpty()) {
            throw new GlobalException("Contact not found");
        }
        var contact = contactOpt.get();
        contact.update(contactDTO);
        return repository.save(contact);
    }

    @Transactional
    public void delete(Long id) {
        var contact = repository.findById(id)
                .orElseThrow(() -> new GlobalException("Contact not found! "));
        repository.deleteById(id);

    }

}
