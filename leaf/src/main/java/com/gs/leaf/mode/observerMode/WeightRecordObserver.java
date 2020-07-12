package com.gs.leaf.mode.observerMode;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Observable;
import java.util.Observer;

@Component
@Slf4j
public class WeightRecordObserver implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof RecordObservable){
            log.warn("WeightRecordObserver arg :{}",arg);
        }
    }
}
