package com.bulvee.decorator.dao;

import com.bulvee.decorator.model.Contact;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CacheAgendaDAO implements DAO {

    private static Map<Long, Contact> cachedContacts = new HashMap<>();
    private DAO agendaDAOIml;

    public CacheAgendaDAO(AgendaDAOImpl agendaDAOIml) {
        this.agendaDAOIml = agendaDAOIml;
    }

    @Override
    public void insert(Contact contact) {

    }

    @Override
    public Optional<Contact> findOne(Long code) {
        Contact contact = cachedContacts.get(code);

        if (contact == null) {
            Optional<Contact> contactOptional = agendaDAOIml.findOne(code);
            contactOptional.ifPresent(c ->
                    cachedContacts.put(c.getCode(), c)
            );
            return contactOptional;
        } else {
            return Optional.of(contact);
        }
    }
}
