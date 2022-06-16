package com.bulvee.decorator.service;

import com.bulvee.decorator.model.Contact;
import com.bulvee.decorator.model.ContactList;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.thoughtworks.xstream.XStream;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class FileConsolidatorServiceImpl implements FileConsolidatorService {

    private AtomicLong code = new AtomicLong(1);

    public FileConsolidatorServiceImpl() {

    }

    @Override
    public void consolidate(String fileName) throws URISyntaxException {

        ContactList contactList = new ContactList();

        List<String[]> list = getNamesFromCSV(fileName);
        list.forEach(name -> {
            contactList.getContacts().add(new Contact(code.getAndIncrement(), name[0], generatePhoneNumber()));
        });

        XStream xStream = new XStream();
        xStream.alias("contact-list", ContactList.class);
        xStream.alias("contact", Contact.class);


        try (OutputStream out = new FileOutputStream("./src/main/resources/contacts.xml")) {
            xStream.toXML(contactList, out);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String[]> getNamesFromCSV(String fileName) throws URISyntaxException {
        List<String[]> list = new ArrayList<>();
        Path path = Paths.get(ClassLoader.getSystemResource(fileName).toURI());
        try (Reader reader = Files.newBufferedReader(path); CSVReader csvReader = new CSVReader(reader)) {
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        List<String> names = new ArrayList<>();

        list.forEach(line -> names.add(line[0].toString()));
        return list;
    }

    private String generatePhoneNumber() {

        StringBuilder phoneNumber = new StringBuilder();

//        int upperbound = 10;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int number = random.nextInt(10);
            phoneNumber.append(number);
        }

        return phoneNumber.toString();
    }
}
