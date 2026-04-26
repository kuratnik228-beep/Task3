package com.example.timeserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimeServer implements Subject {

    private int tick = 0;
    private final List<IObserver> observers = new ArrayList<>();

    public TimeServer() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                tick++;
                notifyAllObservers();
            }
        }, 1000, 1000);
    }

    public int getTick() {
        return tick;
    }

    @Override
    public void attach(IObserver o) {
        observers.add(o);
    }

    @Override
    public void detach(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyAllObservers() {
        for (IObserver o : observers) {
            o.update(this);
        }
    }
}