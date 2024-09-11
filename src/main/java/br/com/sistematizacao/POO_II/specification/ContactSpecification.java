package br.com.sistematizacao.POO_II.specification;

import br.com.sistematizacao.POO_II.entities.Contact;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ContactSpecification {

    public static Specification<Contact> param(String name, String phoneNumber) {
        return (Root<Contact> root, CriteriaQuery<?> query, CriteriaBuilder builder ) -> {
            Predicate p = builder.conjunction();

            if(name != null) {
                p = builder.and(p, builder.like(root.get("name"), "%" + name + "%"));
            }

            if(phoneNumber != null) {
                p = builder.and(p, builder.like(root.get("phoneNumber"), "%" + phoneNumber + "%"));
            }
            return p;
        };
    }

}
