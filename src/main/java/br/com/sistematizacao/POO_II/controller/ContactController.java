package br.com.sistematizacao.POO_II.controller;

import br.com.sistematizacao.POO_II.dtos.ContactListDTO;
import br.com.sistematizacao.POO_II.dtos.ContactSaveDTO;
import br.com.sistematizacao.POO_II.dtos.ContactUpdateDTO;
import br.com.sistematizacao.POO_II.entities.Contact;
import br.com.sistematizacao.POO_II.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ContactSaveDTO contactSaveDTO, UriComponentsBuilder builder) {
        var contact = service.saveContact(contactSaveDTO);
        var uri = builder.path("/contacts/{id}").buildAndExpand(contact.getId()).toUri();
        return ResponseEntity.created(uri).body(contact);
    }

    @GetMapping
    public ResponseEntity<Page<ContactListDTO>> listAllContacts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phoneNumber,
            @PageableDefault(size = 25, sort = {"name"})
            Pageable page) {
        var contact = service.listByParam(name, phoneNumber, page);
        return ResponseEntity.ok().body(contact);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contact> listById(@PathVariable Long id) {
        var contact = service.findContactById(id);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ContactUpdateDTO contactDTO, @PathVariable Long id) {
        try {
            var contact = service.updateContact(contactDTO, id);
            return ResponseEntity.ok().body(contact);
        }catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContactById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().body("Contact deleted successfully.");
    }

}
