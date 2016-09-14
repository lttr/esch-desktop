package cz.lttr.esch.model;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by Lukas Trumm on 13.09.2016
 */
public class ProgramBlock {

    private String name;

    private LocalTime startTime;

    private Duration duration;

    private Settings settings;

    public ProgramBlock(String name, LocalTime startTime, Duration duration, Settings settings) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
        this.settings = settings;
    }

    public LocalTime getStartTime() {
        return roundTimeUp(startTime, settings.getBasicTimeInterval());
    }

    public LocalTime getEndTime() {
        LocalTime exactEndTime = startTime.plus(duration);
        return roundTimeUp(exactEndTime, settings.getBasicTimeInterval());
    }

    private LocalTime roundTimeUp(LocalTime time, int minutesMilestone) {
        int minutes = time.getMinute();
        int plusMinutes = (minutesMilestone + 60 - minutes) % minutesMilestone;
        return time.plusMinutes(plusMinutes);
    }
}
