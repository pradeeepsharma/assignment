package com.gartner.assignemnt;

import com.gartner.assignemnt.domain.Click;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClickFileReader implements ClickReader {
    private static final String FORMAT = "d/MM/yyyy HH:mm:ss";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);

    @Override
    public List<Click> readClicks() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);
        JSONParser parser = new JSONParser();
        JSONArray clicksAsJsonArray = null;
        List<Click> clicks = new ArrayList<>();
        try {
            clicksAsJsonArray = (JSONArray) parser.parse(new FileReader("clicks.json"));
            clicksAsJsonArray.stream()
                    .map(obj ->
                    {
                        var jsonObj = (JSONObject) obj;
                        Click clickObj = null;
                        clickObj = Click.builder()
                                .ip((String) jsonObj.get("ip"))
                                .timestamp(LocalDateTime.parse((String) jsonObj.get("timestamp"), formatter))
                                .period(LocalDateTime.parse((String) jsonObj.get("timestamp"), formatter).getHour())//added extra field for later use in sorting
                                .amount((Double) jsonObj.get("amount"))
                                .build();
                        return clickObj;
                    })
                    .forEach(clickObj -> clicks.add((Click) clickObj));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return clicks;
    }
}
