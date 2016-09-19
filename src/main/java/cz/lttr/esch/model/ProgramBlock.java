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
        return roundTimeUp(startTime, settings.getBasicTimeMilestone());
    }

    public LocalTime getEndTime() {
        LocalTime exactEndTime = startTime.plus(duration);
        return roundTimeUp(exactEndTime, settings.getBasicTimeMilestone());
    }

    public void plusDuration(Duration plusDuration) {
        duration.plus(plusDuration);
    }

    public void minusDuration(Duration minusDuration) {
        duration.minus(minusDuration);
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Rounds the given time up to a multiple of given minutesMilestone.
     *
     * @param time LocalTime with hours and minutes
     * @param minutesMilestone a number of minutes between 1 and 60
     * @return LocalTime with hours and minutes rounded to a multiple of the milestone
     */
    private LocalTime roundTimeUp(LocalTime time, int minutesMilestone) {
        assert 1 <= minutesMilestone;
        assert minutesMilestone <= 60;
        int minutes = time.getMinute();
        // 60 + milestone creates just big enough number which is a multiple of milestone
        int plusMinutes = (60 + minutesMilestone - minutes) % minutesMilestone;
        return time.plusMinutes(plusMinutes);
    }
}
