package com.bulvee.decorator.dao;

import com.bulvee.decorator.model.Contact;

import java.util.Optional;

public interface DAO {

    void insert(Contact contact);
    Optional<Contact> findOne(Long code);
}
