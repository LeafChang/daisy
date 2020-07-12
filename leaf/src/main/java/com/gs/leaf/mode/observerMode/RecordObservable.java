package com.gs.leaf.mode.observerMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Component
public class RecordObservable extends Observable {

    @Autowired
    List<Observer> observers;

    public void sendMessage(String msg){

        observers.stream().forEach(o->addObserver(o));
        setChanged();
        notifyObservers(msg);

    }



}
