package com.example.timeserver;

public interface Subject {
    void attach(IObserver o);
    void detach(IObserver o);
    void notifyAllObservers();
}