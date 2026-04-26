package com.example.timeserver;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class TimerComponent implements IObserver {

    private boolean running = false;
    private int startTick;
    private int duration;
    private final Label label;

    public TimerComponent(Label label) {
        this.label = label;
    }

    public void start(int currentTick, int duration) {
        this.startTick = currentTick;
        this.duration = duration;
        this.running = true;
    }

    public void reset() {
        running = false;
        Platform.runLater(() -> label.setText("—"));
    }

    @Override
    public void update(Subject subject) {
        if (!running) return;

        TimeServer server = (TimeServer) subject;
        int remaining = duration - (server.getTick() - startTick);

        Platform.runLater(() -> {
            if (remaining > 0) {
                label.setText(String.valueOf(remaining));
            } else {
                label.setText("ГОТОВО");
                running = false;
            }
        });
    }
}