package com.gartner.assignemnt.repository;

import com.gartner.assignemnt.domain.Click;

import java.util.List;
/*
Interface to get data in domain specific format.
To support future extension to read data from data base or a queue.
 */
public interface ClickRepository {
    List<Click> readClicks();
    void writeClick(List<Click> subSetOfOptionals);
}
