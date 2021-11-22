package com.gartner.assignemnt.controller;

import com.gartner.assignemnt.service.ScreeningService;

public class ClickScanner {
    public static void main(String[] args) {
        //Static deceleration of dependencies, could be replaced with runtime injection with framework support
        ScreeningService service = new ScreeningService();
        service.scanRelevantIPs();

    }
}
