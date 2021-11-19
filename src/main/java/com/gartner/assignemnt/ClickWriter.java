package com.gartner.assignemnt;

import com.gartner.assignemnt.domain.Click;

import java.util.List;

public interface ClickWriter {
    void writeClickOutput(List<Click> subSetOfOptionals);
}
