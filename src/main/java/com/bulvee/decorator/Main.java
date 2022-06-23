package com.bulvee.decorator;

import com.bulvee.decorator.dao.DAO;
import com.bulvee.decorator.dao.AgendaDAOImpl;
import com.bulvee.decorator.dao.CacheAgendaDAO;
import com.bulvee.decorator.model.Contact;
import com.bulvee.decorator.service.FileConsolidatorService;
import com.bulvee.decorator.service.FileConsolidatorServiceImpl;

import java.net.URISyntaxException;
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


        Path path = Paths.get(ClassLoader.getSystemResource("contacts.xml").toURI());

        //Execute decorator implementation
        DAO agendaDAO = new CacheAgendaDAO(new AgendaDAOImpl(path.toAbsolutePath().toString()));

        Optional<Contact> first = agendaDAO.findOne(40l);
        Optional<Contact> one = agendaDAO.findOne(40l);

        System.out.println(System.currentTimeMillis());
        System.out.println(one.get());

        System.out.println(one.get());


    }
}
