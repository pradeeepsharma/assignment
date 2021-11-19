package com.gartner.assignemnt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gartner.assignemnt.domain.Click;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ClickFileWriter implements ClickWriter {

    @Override
    public void writeClickOutput(List<Click> subSetOfClicks) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        try {
            mapper.writeValue(new File("resultset.json"), subSetOfClicks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
