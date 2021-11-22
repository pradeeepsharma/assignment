package com.gartner.assignemnt.service;

import com.gartner.assignemnt.domain.Click;
import com.gartner.assignemnt.repository.ClickRepositoryJsonImpl;
import com.gartner.assignemnt.repository.ClickRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScreeningService {
    // fixed dependencies for now, but could be injected with any framework support like spring
    ClickRepository clickRepository = new ClickRepositoryJsonImpl();

    public void scanRelevantIPs() {
        //Collecting only troublesome IPs in list
        List<Click> filterTroublesomeClicks = filterTroublesomeClicks(readJsonInRelevantFormat());

        //Writing values to file
        clickRepository.writeClick(filterTroublesomeClicks);
    }

    protected Map<String, Map<Integer, Optional<Click>>> readJsonInRelevantFormat() {

        return clickRepository.readClicks().stream()
                .collect(Collectors.groupingBy(Click::getIp,
                        Collectors.groupingBy(Click::getPeriod,
                                Collectors.maxBy((c1, c2) -> c1.getAmount().compareTo(c2.getAmount())))));
    }


    protected List<Click> filterTroublesomeClicks(Map<String, Map<Integer, Optional<Click>>> mappedByIPAndPeriod) {
        List<Click> troublesomeClicks = new ArrayList<>();
        mappedByIPAndPeriod.values().stream()
                .filter(mappedByPeriod -> mappedByPeriod.size() <= 10)  //ignored ips with more than 10 occurrence
                .forEach(mappedByPeriod -> {
                    troublesomeClicks.addAll(mappedByPeriod
                            .values()
                            .stream()
                            .map(optionalClick -> optionalClick.get()).toList());
                });
        return troublesomeClicks;
    }
}
