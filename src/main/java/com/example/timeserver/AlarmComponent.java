package com.example.timeserver;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class AlarmComponent implements IObserver {

    private boolean armed = false;
    private int alarmTick;
    private final Label label;

    public AlarmComponent(Label label) {
        this.label = label;
    }

    public void start(int alarmTick) {
        this.alarmTick = alarmTick;
        this.armed = true;

        javafx.application.Platform.runLater(() ->
                label.setText("Будильник установлен на тик " + alarmTick)
        );
    }

    public void reset() {
        armed = false;
        Platform.runLater(() -> label.setText("—"));
    }

    @Override
    public void update(Subject subject) {
        if (!armed) return;

        TimeServer server = (TimeServer) subject;

        if (server.getTick() == alarmTick) {
            Platform.runLater(() -> label.setText("🔔"));
            armed = false;
        }
    }
}