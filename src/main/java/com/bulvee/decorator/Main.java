package com.bulvee.decorator;

import com.bulvee.decorator.dao.AgendaDAO;
import com.bulvee.decorator.dao.AgendaDAOImpl;
import com.bulvee.decorator.dao.CacheAgendaDAO;
import com.bulvee.decorator.model.Contact;
import com.bulvee.decorator.service.FileConsolidatorService;
import com.bulvee.decorator.service.FileConsolidatorServiceImpl;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

        //Prepare environment
        FileConsolidatorService fileConsolidatorService = new FileConsolidatorServiceImpl();

        try {
            fileConsolidatorService.consolidate("random_names_fossbytes.csv");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        //Execute objective

        Path path = Paths.get(ClassLoader.getSystemResource("contacts.xml").toURI());

        AgendaDAO agendaDAO = new CacheAgendaDAO(new AgendaDAOImpl(path.toAbsolutePath().toString()));

        Optional<Contact> first = agendaDAO.findOne(40l);
        Optional<Contact> one = agendaDAO.findOne(40l);

        System.out.println(System.currentTimeMillis());
        System.out.println(one.get());

        System.out.println(one.get());


    }
}
