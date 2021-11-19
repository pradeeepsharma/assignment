package com.gartner.assignemnt;

import com.gartner.assignemnt.domain.Click;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class IPRegister {
    private static List<Click> troublesomeClicks = new ArrayList<>();

    public static void main(String[] args) {

        //Collecting only troublesome IPs in list
        collectRelaventIps(readJsonInRelaventFormat());

        //Writing values to file
        ClickWriter writer = new ClickFileWriter();
        writer.writeClickOutput(troublesomeClicks);
    }

    private static Map<String, Map<Integer, Optional<Click>>> readJsonInRelaventFormat() {
        ClickReader reader = new ClickFileReader();
        return reader.readClicks().stream()
                .collect(Collectors.groupingBy(Click::getIp, Collectors.groupingBy(Click::getPeriod, Collectors.maxBy((c1, c2) -> c1.getAmount().compareTo(c2.getAmount())))));
    }

    private static void collectRelaventIps(Map<String, Map<Integer, Optional<Click>>> mappedByIPAndPeriod) {

        mappedByIPAndPeriod.values().stream()
                .filter(mappedByPeriod -> mappedByPeriod.size() <= 10)  //ignored ips with more than 10 occurrence
                .forEach(mappedByPeriod -> {
                    troublesomeClicks.addAll(mappedByPeriod.values().stream().map(optionalClick -> optionalClick.get()).toList());
                });
    }
}
