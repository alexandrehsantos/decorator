package com.bulvee.decorator.service;

import java.net.URISyntaxException;

public interface FileConsolidatorService {
   
    void consolidate(String fileName) throws URISyntaxException;
}
