package com.example.timeserver;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML private Label tickLabel;
    @FXML private Label stopwatchLabel;
    @FXML private Label timerLabel;
    @FXML private Label alarmLabel;

    @FXML private TextField timerField;
    @FXML private TextField alarmField;

    private final TimeServer server = new TimeServer();

    private StopwatchComponent stopwatch;
    private TimerComponent timer;
    private AlarmComponent alarm;

    @FXML
    public void initialize() {
        stopwatch = new StopwatchComponent(stopwatchLabel);
        timer = new TimerComponent(timerLabel);
        alarm = new AlarmComponent(alarmLabel);

        server.attach(s ->
                javafx.application.Platform.runLater(() ->
                        tickLabel.setText("Тики: " + ((TimeServer)s).getTick())
                )
        );
        server.attach(stopwatch);
        server.attach(timer);
        server.attach(alarm);
    }

    @FXML public void startStopwatch() {
        stopwatch.start(server.getTick());
    }

    @FXML public void stopStopwatch() {
        stopwatch.stop();
    }

    @FXML public void resetStopwatch() {
        stopwatch.reset();
    }

    @FXML public void startTimer() {
        timer.start(server.getTick(), Integer.parseInt(timerField.getText()));
    }

    @FXML public void resetTimer() {
        timer.reset();
    }

    @FXML
    public void startAlarm() {
        int alarmTick = Integer.parseInt(alarmField.getText());
        int currentTick = server.getTick();

        if (alarmTick <= currentTick) {
            alarmLabel.setText(
                    "Нельзя поставить будильник на прошедший тик (" + currentTick + " тик)"
            );
            return;
        }

        alarm.start(alarmTick);
    }

    @FXML public void resetAlarm() {
        alarm.reset();
    }
}