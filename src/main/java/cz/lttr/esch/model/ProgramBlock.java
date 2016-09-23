package cz.lttr.esch.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by Lukas Trumm on 13.09.2016
 */
public class ProgramBlock {

    private static final Logger logger = LoggerFactory.getLogger(ProgramBlock.class);

    private String name;

    private LocalTime startTime;

    private Duration duration;

    private Settings settings;

    public ProgramBlock(String name, LocalTime startTime, Duration duration, Settings settings) {
        this.name = name;
        setDuration(duration);
        setStartTime(startTime);
        this.settings = settings;
    }

    public LocalTime getStartTime() {
        return roundTimeUp(startTime, settings.getBasicTimeMilestoneInMinutes());
    }

    public LocalTime getEndTime() {
        LocalTime exactEndTime = startTime.plus(duration);
        return roundTimeUp(exactEndTime, settings.getBasicTimeMilestoneInMinutes());
    }

    public void plusDuration(Duration plusDuration) {
        this.duration = duration.plus(plusDuration);
    }

    public void minusDuration(Duration minusDuration) {
        setDuration(duration.minus(minusDuration));
    }

    /**
     * Makes sure the new start time will not cause the end time to be after midnight.
     *
     * @param newStartTime LocalTime which is intended to be set
     */
    public void setStartTime(LocalTime newStartTime) {
        LocalTime maxStartTime = LocalTime.MIDNIGHT.minus(duration);
        if (newStartTime.isAfter(maxStartTime)) {
            this.startTime = maxStartTime;
            logger.debug("New start time %s was replaced with maximal start time %s in order to not " +
                    "exceed midnight.", newStartTime, maxStartTime);
        } else {
            this.startTime = newStartTime;
        }
    }

    public Duration getDuration() {
        return duration;
    }


    /**
     * Makes sure duration is not negative.
     */
    private void setDuration(Duration newDuration) {
        Duration maxDuration = Duration.ofHours(23);
        Duration minDuration = Duration.ZERO;

        if (newDuration.isNegative()) {
            this.duration = minDuration;
            logger.debug("Duration was set to minimal duration of %s", minDuration);
        } else {
            if (newDuration.compareTo(maxDuration) > 0) {
                this.duration = maxDuration;
                logger.debug("Duration was cut to maximal duration of %s hours.", maxDuration);
            } else {
                this.duration = newDuration;
            }
        }
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
