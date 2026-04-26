package com.example.timeserver;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class StopwatchComponent implements IObserver {

    private boolean running = false;

    private int startTick = 0;      // тик, с которого считаем
    private int elapsedTicks = 0;   // сколько уже прошло

    private final Label label;

    public StopwatchComponent(Label label) {
        this.label = label;
    }

    public void start(int currentTick) {
        // если продолжаем — учитываем уже прошедшее
        startTick = currentTick - elapsedTicks;
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        running = false;
        elapsedTicks = 0;

        Platform.runLater(() ->
                label.setText("0")
        );
    }

    @Override
    public void update(Subject subject) {
        if (!running) return;

        TimeServer server = (TimeServer) subject;

        elapsedTicks = server.getTick() - startTick;

        Platform.runLater(() ->
                label.setText(String.valueOf(elapsedTicks))
        );
    }
}