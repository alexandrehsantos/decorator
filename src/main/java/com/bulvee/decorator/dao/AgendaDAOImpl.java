package com.bulvee.decorator.dao;

import com.bulvee.decorator.model.Contact;
import com.bulvee.decorator.model.ContactList;
import com.thoughtworks.xstream.XStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AgendaDAOImpl implements DAO {

    private String dataFileName;

    public AgendaDAOImpl(String dataFileName) {
        this.dataFileName = dataFileName;
    }


    @Override
    public void insert(Contact contact) {

    }

    @Override
    public Optional<Contact> findOne(Long code) {

        XStream xStream = new XStream();
        xStream.alias("contact-list", ContactList.class);
        xStream.alias("contact", Contact.class);
        xStream.allowTypeHierarchy(ContactList.class);
        xStream.allowTypeHierarchy(Contact.class);

        ContactList contactList = null;

        try (FileInputStream file = new FileInputStream(this.dataFileName)) {
             contactList = (ContactList) xStream.fromXML(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Contact> collect = contactList.getContacts().stream().filter(contact -> contact.getCode().equals(code)).collect(Collectors.toList());


        return collect.stream().findAny();
    }
}
